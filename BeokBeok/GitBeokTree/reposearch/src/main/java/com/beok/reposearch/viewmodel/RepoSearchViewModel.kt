package com.beok.reposearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beok.common.Result
import com.beok.common.base.BaseViewModel
import com.beok.common.succeeded
import com.beok.reposearch.entity.GithubRepoResEntity
import com.beok.reposearch.usecase.UserRepoSearchUseCase
import kotlinx.coroutines.launch

class RepoSearchViewModel(
    private val userRepoSearchUseCase: UserRepoSearchUseCase
) : BaseViewModel() {

    private val _repoList = MutableLiveData<List<GithubRepoResEntity>>()
    private val _errMsg = MutableLiveData<Throwable>()

    val repoList: LiveData<List<GithubRepoResEntity>> get() = _repoList
    val errMsg: LiveData<Throwable> get() = _errMsg

    fun searchUserRepo(user: String) = viewModelScope.launch {
        val remoteRepos = userRepoSearchUseCase(user)
        if (remoteRepos.succeeded) {
            _repoList.value = (remoteRepos as Result.Success).data
        } else {
            _errMsg.value =
                (remoteRepos as? Result.Error)?.exception ?: IllegalStateException("Data is null")
        }
    }
}