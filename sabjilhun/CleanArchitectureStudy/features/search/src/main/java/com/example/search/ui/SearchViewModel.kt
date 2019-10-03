package com.example.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.base.BaseViewModel
import com.example.common.commons.SingleLiveEvent
import com.example.common.ext.addTo
import com.example.common.utils.TextUtils
import com.example.domains.entities.RepositorySummaryInfo
import com.example.domains.usecases.SearchRepositoriesUseCase
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : BaseViewModel() {

    val query = MutableLiveData<String>("")

    private val _repositories = MutableLiveData<List<RepositorySummaryInfo>>()
    val repositories: LiveData<List<RepositorySummaryInfo>> = _repositories

    private val _alertEmptyQuery = SingleLiveEvent<Unit>()
    val alertEmptyQuery: LiveData<Unit> = _alertEmptyQuery

    fun searchRepositories() {
        if (TextUtils.isEmpty(query.value)) {
            _alertEmptyQuery.setValue(Unit)
            return
        }

        searchRepositoriesUseCase.getRepositories(query.value!!)
            .compose(apiLoadingTransformer())
            .subscribeBy(
                onSuccess = _repositories::setValue,
                onError = ::handleApiErrorMessage
            ).addTo(disposables)
    }
}