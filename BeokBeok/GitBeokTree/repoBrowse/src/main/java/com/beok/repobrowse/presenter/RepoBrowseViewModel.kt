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
        val remoteRepoFileTree = userRepoBrowseUsecase(
            userName,
            repoName
        )
        if (!remoteRepoFileTree.succeeded) {
            setRepoBrowseData(
                err = (remoteRepoFileTree as? Result.Error)?.exception
                    ?: IllegalStateException("Data is null")
            )
            return@launch
        }
        setUserAndRepoName(userName, repoName)
        setRepoBrowseData(
            repoFileTree = (remoteRepoFileTree as Result.Success).data
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
        this@RepoBrowseViewModel.userName = userName
        this@RepoBrowseViewModel.repoName = repoName
    }

    private fun setRepoBrowseData(
        repoFileTree: List<RepoFileTreeEntity> = listOf(),
        err: Throwable = IllegalStateException("")
    ) {
        _repoFileTree.value = repoFileTree
        if (!err.message.isNullOrEmpty()) _errMsg.value = err
    }
}