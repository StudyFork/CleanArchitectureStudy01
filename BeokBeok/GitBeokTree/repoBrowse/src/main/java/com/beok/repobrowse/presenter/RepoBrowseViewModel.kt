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

    fun showRepoFileTree(
        userName: String,
        repoName: String
    ) = viewModelScope.launch {
        updateRepoFileTree(
            repoFileTreeList = userRepoBrowseUsecase(
                userName,
                repoName
            )
        )
        setUserAndRepoName(userName, repoName)
    }

    fun showSpecificDir(detail: String) = viewModelScope.launch {
        updateRepoFileTree(
            repoFileTreeList = userRepoBrowseUsecase(
                userName,
                repoName,
                detail
            )
        )
    }

    private fun updateRepoFileTree(repoFileTreeList: Result<List<RepoFileTreeEntity>>) {
        if (!repoFileTreeList.succeeded) {
            setRepoBrowseData(
                err = (repoFileTreeList as? Result.Error)?.exception
                    ?: IllegalStateException("Data is null")
            )
            return
        }
        setRepoBrowseData(
            repoFileTree = (repoFileTreeList as Result.Success).data
                .asSequence()
                .sortedWith(
                    compareBy(
                        RepoFileTreeEntity::type,
                        RepoFileTreeEntity::path
                    )
                )
                .toList()
        )
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
        _repoFileTree.value = getRepoFileTreeItem(repoFileTree)
        if (!err.message.isNullOrEmpty()) _errMsg.value = err
    }

    private fun getRepoFileTreeItem(
        repoFileTreeItem: List<RepoFileTreeEntity>
    ): List<RepoFileTreeEntity> {
        val updatedRepoFileTreeItem = mutableListOf<RepoFileTreeEntity>()
        _repoFileTree.value?.let {
            updatedRepoFileTreeItem.addAll(it)
        } ?: run {
            updatedRepoFileTreeItem.addAll(repoFileTreeItem)
            return updatedRepoFileTreeItem
        }

        val parentIndex = getParentIndex(
            allFileTree = updatedRepoFileTreeItem,
            childFileTree = repoFileTreeItem
        )
        if (parentIndex >= 0) {
            updatedRepoFileTreeItem.addAll(parentIndex + 1, repoFileTreeItem)
        }
        return updatedRepoFileTreeItem
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
}