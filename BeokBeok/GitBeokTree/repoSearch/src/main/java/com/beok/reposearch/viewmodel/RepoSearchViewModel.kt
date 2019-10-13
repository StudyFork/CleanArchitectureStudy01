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
        if (_userName.value == user) return@launch
        showProgressBar()
        val remoteRepos = userRepoSearchUsecase(user)
        if (remoteRepos.succeeded) {
            setRepoSearchData(
                userName = user,
                repoList = (remoteRepos as Result.Success).data
            )
        } else {
            setRepoSearchData(
                userName = "",
                repoList = listOf(),
                err = (remoteRepos as? Result.Error)?.exception
                    ?: IllegalStateException("Data is null")
            )
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

    private fun setRepoSearchData(
        userName: String,
        repoList: List<RepoResEntity>,
        err: Throwable = IllegalStateException("")
    ) {
        _userName.value = userName
        _repoList.value = repoList
        if (!err.message.isNullOrEmpty()) _errMsg.value = err
    }
}