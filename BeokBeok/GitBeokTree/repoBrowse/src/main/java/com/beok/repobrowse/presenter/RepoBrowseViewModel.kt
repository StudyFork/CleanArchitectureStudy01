package com.beok.repobrowse.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beok.common.Result
import com.beok.common.base.BaseViewModel
import com.beok.common.succeeded
import com.beok.repobrowse.domain.entity.RepoFileTreeEntity
import com.beok.repobrowse.domain.usecase.UserRepoBrowseUsecase
import kotlinx.coroutines.launch

class RepoBrowseViewModel(
    private val userRepoBrowseUsecase: UserRepoBrowseUsecase
) : BaseViewModel() {

    private val _repoFileTree = MutableLiveData<List<RepoFileTreeEntity>>()
    private val _errMsg = MutableLiveData<Throwable>()

    private lateinit var userName: String
    private lateinit var repoName: String

    val repoFileTree: LiveData<List<RepoFileTreeEntity>> get() = _repoFileTree
    val errMsg: LiveData<Throwable> get() = _errMsg

    fun showRepoBrowser(
        userName: String,
        repoName: String
    ) = viewModelScope.launch {
        addRepoFileTree(
            repoFileTreeItems = userRepoBrowseUsecase(
                userName,
                repoName
            )
        )
        setUserAndRepoName(userName, repoName)
    }

    fun clickSpecificItem(selectedItem: RepoFileTreeEntity) = viewModelScope.launch {
        if (selectedItem.type == "dir") {
            if (selectedItem.expandable) {
                addRepoFileTree(
                    repoFileTreeItems = userRepoBrowseUsecase(
                        userName,
                        repoName,
                        selectedItem.path
                    )
                )
            } else {
                removeRepoFileTree(selectedItem)
            }
        } else {
            clickFileItem(selectedItemUrl = selectedItem.downloadUrl)
        }
    }

    private fun clickFileItem(selectedItemUrl: String) =
        navigate(RepoBrowseFragmentDirections.actionRepobrowseToFileviewer(selectedItemUrl))

    private fun addRepoFileTree(repoFileTreeItems: Result<List<RepoFileTreeEntity>>) {
        if (!repoFileTreeItems.succeeded) {
            setRepoBrowseData(
                err = (repoFileTreeItems as? Result.Error)?.exception
                    ?: IllegalStateException("Data is null")
            )
            return
        }
        val addedRepoFileTree = getAddedRepoFileTree(
            (repoFileTreeItems as Result.Success).data
                .asSequence()
                .sortedWith(
                    compareBy(
                        RepoFileTreeEntity::type,
                        RepoFileTreeEntity::path
                    )
                )
                .toList()
        )
        setRepoBrowseData(repoFileTree = addedRepoFileTree)
    }

    private fun setUserAndRepoName(
        userName: String,
        repoName: String
    ) {
        this@RepoBrowseViewModel.let {
            it.userName = userName
            it.repoName = repoName
        }
    }

    private fun setRepoBrowseData(
        repoFileTree: List<RepoFileTreeEntity> = emptyList(),
        err: Throwable = IllegalStateException("")
    ) {
        _repoFileTree.value = repoFileTree
        if (!err.message.isNullOrEmpty()) _errMsg.value = err
    }

    private fun getAddedRepoFileTree(itemToAdd: List<RepoFileTreeEntity>): List<RepoFileTreeEntity> {
        val addedRepoFileTreeItem = mutableListOf<RepoFileTreeEntity>()
        _repoFileTree.value?.let {
            addedRepoFileTreeItem.addAll(it)
        } ?: run {
            addedRepoFileTreeItem.addAll(itemToAdd)
            return addedRepoFileTreeItem
        }

        val parentIndex = getParentIndex(
            allFileTree = addedRepoFileTreeItem,
            childFileTree = itemToAdd
        )
        if (parentIndex >= 0) {
            addedRepoFileTreeItem.addAll(parentIndex + 1, itemToAdd)
            addedRepoFileTreeItem[parentIndex].expandable = false
        }
        return addedRepoFileTreeItem
    }

    private fun getParentIndex(
        allFileTree: List<RepoFileTreeEntity>,
        childFileTree: List<RepoFileTreeEntity>
    ): Int {
        val parentElement = allFileTree.asSequence()
            .find { it.path.plus("/${childFileTree[0].name}") == childFileTree[0].path }
        return if (parentElement != null) {
            allFileTree.indexOf(parentElement)
        } else {
            -1
        }
    }

    private fun removeRepoFileTree(parentItem: RepoFileTreeEntity) {
        val removedRepoFileTreeItem = mutableListOf<RepoFileTreeEntity>()
        _repoFileTree.value?.let {
            removedRepoFileTreeItem.addAll(it)
        } ?: return

        removedRepoFileTreeItem[removedRepoFileTreeItem.indexOf(parentItem)].expandable = true
        setRepoBrowseData(
            repoFileTree = removedRepoFileTreeItem.asSequence()
                .filterNot {
                    it.path.startsWith(parentItem.path.plus("/"))
                }
                .toList()
        )
    }
}