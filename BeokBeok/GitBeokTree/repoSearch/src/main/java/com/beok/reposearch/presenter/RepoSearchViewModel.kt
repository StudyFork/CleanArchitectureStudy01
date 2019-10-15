package com.beok.reposearch.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beok.common.Result
import com.beok.common.base.BaseViewModel
import com.beok.common.succeeded
import com.beok.reposearch.domain.entity.ReposEntity
import com.beok.reposearch.domain.usecase.UserRepoSearchUsecase
import kotlinx.coroutines.launch

class RepoSearchViewModel(
    private val userRepoSearchUsecase: UserRepoSearchUsecase
) : BaseViewModel() {

    private val _repoList = MutableLiveData<List<ReposEntity>>()
    private val _errMsg = MutableLiveData<Throwable>()
    private val _isLoading = MutableLiveData<Boolean>(false)
    private val _userName = MutableLiveData<String>("")

    val repoList: LiveData<List<ReposEntity>> get() = _repoList
    val errMsg: LiveData<Throwable> get() = _errMsg
    val isLoading: LiveData<Boolean> get() = _isLoading
    val userName: LiveData<String> get() = _userName

    fun searchUserRepo(user: String) = viewModelScope.launch {
        if (_userName.value == user) return@launch // 같은 이름 검색 시, observe 방지

        showProgressBar()
        val remoteRepos = userRepoSearchUsecase(user)
        hideProgressBar()
        if (!remoteRepos.succeeded) {
            setRepoSearchData(
                err = (remoteRepos as? Result.Error)?.exception
                    ?: IllegalStateException("Data is null")
            )
            return@launch
        }
        setRepoSearchData(
            userName = user,
            repoList = (remoteRepos as Result.Success).data
        )
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
        userName: String = "",
        repoList: List<ReposEntity> = listOf(),
        err: Throwable = IllegalStateException("")
    ) {
        _userName.value = userName
        _repoList.value = repoList
        if (!err.message.isNullOrEmpty()) _errMsg.value = err
    }
}