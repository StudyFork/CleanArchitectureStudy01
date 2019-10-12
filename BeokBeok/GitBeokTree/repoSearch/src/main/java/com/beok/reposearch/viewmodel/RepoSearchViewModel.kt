package com.beok.reposearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beok.common.Result
import com.beok.common.base.BaseViewModel
import com.beok.common.succeeded
import com.beok.reposearch.entity.RepoResEntity
import com.beok.reposearch.usecase.UserRepoSearchUsecase
import com.beok.reposearch.view.RepoSearchFragmentDirections
import kotlinx.coroutines.launch

class RepoSearchViewModel(
    private val userRepoSearchUsecase: UserRepoSearchUsecase
) : BaseViewModel() {

    private val _repoList = MutableLiveData<List<RepoResEntity>>()
    private val _errMsg = MutableLiveData<Throwable>()
    private val _isLoading = MutableLiveData<Boolean>(false)
    private val _userName = MutableLiveData<String>("")

    val repoList: LiveData<List<RepoResEntity>> get() = _repoList
    val errMsg: LiveData<Throwable> get() = _errMsg
    val isLoading: LiveData<Boolean> get() = _isLoading
    val userName: LiveData<String> get() = _userName

    fun searchUserRepo(user: String) = viewModelScope.launch {
        showProgressBar()
        val remoteRepos = userRepoSearchUsecase(user)
        if (remoteRepos.succeeded) {
            _userName.value = user
            _repoList.value = (remoteRepos as Result.Success).data
        } else {
            _userName.value = ""
            _repoList.value = listOf()
            _errMsg.value =
                (remoteRepos as? Result.Error)?.exception ?: IllegalStateException("Data is null")
        }
        hideProgressBar()
    }

    fun showRepo(
        user: String,
        repoName: String
    ) = navigate(
        RepoSearchFragmentDirections.actionReposearchToRepobrowse(
            user,
            repoName
        )
    )

    private fun showProgressBar() {
        _isLoading.value = true
    }

    private fun hideProgressBar() {
        _isLoading.value = false
    }

}