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

    val repoFileTree: LiveData<List<RepoFileTreeEntity>> get() = _repoFileTree
    val errMsg: LiveData<Throwable> get() = _errMsg

    fun showRepoFileTree(
        user: String,
        repoName: String
    ) = viewModelScope.launch {
        val remoteRepoFileTree = userRepoBrowseUsecase(
            user,
            repoName
        )
        if (remoteRepoFileTree.succeeded) {
            _repoFileTree.value = (remoteRepoFileTree as Result.Success).data
        } else {
            _errMsg.value = (remoteRepoFileTree as? Result.Error)?.exception
                ?: IllegalStateException("Data is null")
        }
    }

}