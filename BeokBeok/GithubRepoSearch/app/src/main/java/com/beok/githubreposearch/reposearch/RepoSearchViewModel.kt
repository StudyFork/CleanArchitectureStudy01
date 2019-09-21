package com.beok.githubreposearch.reposearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beok.githubreposearch.base.BaseViewModel
import com.beok.githubreposearch.data.RepoSearchRepository
import com.beok.githubreposearch.data.model.Repos
import kotlinx.coroutines.launch

class RepoSearchViewModel(
    private val repoSearchRepository: RepoSearchRepository
) : BaseViewModel() {

    private val _repoList = MutableLiveData<List<Repos>>()
    private val _errMsg = MutableLiveData<Throwable>()

    val repoList: LiveData<List<Repos>> get() = _repoList
    val errMsg: LiveData<Throwable> get() = _errMsg

    fun searchUserRepo(user: String) = viewModelScope.launch {
        repoSearchRepository.getRepoList(
            user,
            onSuccess = { _repoList.value = it },
            onFail = { _errMsg.value = it }
        )
    }
}