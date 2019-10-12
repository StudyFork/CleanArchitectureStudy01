package com.beok.reposearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beok.common.Result
import com.beok.common.base.BaseViewModel
import com.beok.common.succeeded
import com.beok.reposearch.entity.RepoResEntity
import com.beok.reposearch.usecase.UserRepoSearchUseCase
import com.beok.reposearch.view.RepoSearchFragmentDirections
import kotlinx.coroutines.launch

class RepoSearchViewModel(
    private val userRepoSearchUseCase: UserRepoSearchUseCase
) : BaseViewModel() {

    private val _repoList = MutableLiveData<List<RepoResEntity>>()
    private val _errMsg = MutableLiveData<Throwable>()
    private val _isLoading = MutableLiveData<Boolean>(false)

    val repoList: LiveData<List<RepoResEntity>> get() = _repoList
    val errMsg: LiveData<Throwable> get() = _errMsg
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun searchUserRepo(user: String) = viewModelScope.launch {
        setProgressBarState(true)
        val remoteRepos = userRepoSearchUseCase(user)
        if (remoteRepos.succeeded) {
            _repoList.value = (remoteRepos as Result.Success).data
        } else {
            _repoList.value = listOf()
            _errMsg.value =
                (remoteRepos as? Result.Error)?.exception ?: IllegalStateException("Data is null")
        }
        setProgressBarState(false)
    }

    fun showRepo(repoName: String) {
        navigate(RepoSearchFragmentDirections.actionReposearchToRepobrowse(repoName))
    }

    private fun setProgressBarState(state: Boolean) {
        _isLoading.value = state
    }
}