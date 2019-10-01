package com.beok.githubreposearch.reposearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beok.githubreposearch.base.BaseViewModel
import com.beok.githubreposearch.data.Result.Error
import com.beok.githubreposearch.data.Result.Success
import com.beok.githubreposearch.data.domain.RepoSearchUseCase
import com.beok.githubreposearch.data.model.Repos
import com.beok.githubreposearch.data.succeeded
import kotlinx.coroutines.launch

class RepoSearchViewModel(
    private val repoSearchUseCase: RepoSearchUseCase
) : BaseViewModel() {

    private val _repoList = MutableLiveData<List<Repos>>()
    private val _errMsg = MutableLiveData<Throwable>()

    val repoList: LiveData<List<Repos>> get() = _repoList
    val errMsg: LiveData<Throwable> get() = _errMsg

    fun searchUserRepo(user: String) = viewModelScope.launch {
        val remoteRepos = repoSearchUseCase(user)
        if (remoteRepos.succeeded) {
            _repoList.value = (remoteRepos as Success).data
        } else {
            _errMsg.value =
                (remoteRepos as? Error)?.exception ?: IllegalStateException("Data is null")
        }
    }
}