package com.beok.repobrowse.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beok.common.Result
import com.beok.common.base.BaseViewModel
import com.beok.common.succeeded
import com.beok.repobrowse.domain.entity.RepoFileTreeEntity
import com.beok.repobrowse.domain.entity.mappingToPresenter
import com.beok.repobrowse.domain.usecase.UserRepoBrowseUsecase
import kotlinx.coroutines.launch

class RepoBrowseViewModel(
    private val userRepoBrowseUsecase: UserRepoBrowseUsecase
) : BaseViewModel() {

    private val _repoFileTree = MutableLiveData<List<RepoFileTreeItem>>()
    private val _errMsg = MutableLiveData<Throwable>()
    private val _isLoading = MutableLiveData<Boolean>()

    private lateinit var userName: String
    private lateinit var repoName: String

    val repoFileTree: LiveData<List<RepoFileTreeItem>> get() = _repoFileTree
    val errMsg: LiveData<Throwable> get() = _errMsg
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun showRepoBrowser(
        userName: String,
        repoName: String
    ) = viewModelScope.launch {
        showProgressBar()
        addRepoFileTree(
            repoFileTreeEntity = userRepoBrowseUsecase(
                userName,
                repoName
            )
        )
        setUserAndRepoName(userName, repoName)
        hideProgressBar()
    }

    fun clickSpecificItem(selectedItem: RepoFileTreeItem) = viewModelScope.launch {
        if (selectedItem.type == "dir") {
            if (selectedItem.expandable) {
                addRepoFileTree(
                    repoFileTreeEntity = userRepoBrowseUsecase(
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

    private fun addRepoFileTree(repoFileTreeEntity: Result<List<RepoFileTreeEntity>>) {
        if (!repoFileTreeEntity.succeeded) {
            setRepoBrowseData(
                err = (repoFileTreeEntity as? Result.Error)?.exception
                    ?: IllegalStateException("Data is null")
            )
            return
        }
        val addedRepoFileTree = getAddedRepoFileTree(
            (repoFileTreeEntity as Result.Success).data
                .map { it.mappingToPresenter() }
                .asSequence()
                .sortedWith(
                    compareBy(
                        RepoFileTreeItem::type,
                        RepoFileTreeItem::path
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
        repoFileTree: List<RepoFileTreeItem> = emptyList(),
        err: Throwable = IllegalStateException("")
    ) {
        _repoFileTree.value = repoFileTree
        if (!err.message.isNullOrEmpty()) _errMsg.value = err
    }

    private fun getAddedRepoFileTree(itemToAdd: List<RepoFileTreeItem>): List<RepoFileTreeItem> {
        val addedRepoFileTreeItem = mutableListOf<RepoFileTreeItem>()
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
        allFileTree: List<RepoFileTreeItem>,
        childFileTree: List<RepoFileTreeItem>
    ): Int {
        val parentElement = allFileTree.asSequence()
            .find { it.path.plus("/${childFileTree[0].name}") == childFileTree[0].path }
        return if (parentElement != null) {
            allFileTree.indexOf(parentElement)
        } else {
            -1
        }
    }

    private fun removeRepoFileTree(parentItem: RepoFileTreeItem) {
        val removedRepoFileTreeItem = mutableListOf<RepoFileTreeItem>()
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

    private fun showProgressBar() {
        _isLoading.value = true
    }

    private fun hideProgressBar() {
        _isLoading.value = false
    }
}