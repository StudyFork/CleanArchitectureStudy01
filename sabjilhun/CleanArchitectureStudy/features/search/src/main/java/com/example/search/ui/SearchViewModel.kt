package com.example.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.base.BaseViewModel
import com.example.common.ext.addTo
import com.example.domains.entities.RepositorySummaryInfo
import com.example.domains.usecases.SearchRepositoriesUseCase
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : BaseViewModel() {

    private val _repositories = MutableLiveData<List<RepositorySummaryInfo>>()
    val repositories: LiveData<List<RepositorySummaryInfo>> = _repositories

    fun searchRepositories(query: String) {
        searchRepositoriesUseCase.getRepositories(query)
            .compose(apiLoadingTransformer())
            .subscribeBy(
                onSuccess = _repositories::setValue,
                onError = ::handleErrorMessage
            ).addTo(disposables)
    }
}