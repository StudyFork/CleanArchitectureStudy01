package com.beok.githubreposearch.reposearch

import androidx.lifecycle.viewModelScope
import com.beok.githubreposearch.base.BaseViewModel
import com.beok.githubreposearch.data.RepoSearchRepository
import kotlinx.coroutines.launch

class RepoSearchViewModel(
    private val repoSearchRepository: RepoSearchRepository
) : BaseViewModel() {

    fun searchUserRepo(user: String) = viewModelScope.launch {
        repoSearchRepository.getRepoList(
            user,
            onSuccess = {

            },
            onFail = {

            }
        )
    }
}