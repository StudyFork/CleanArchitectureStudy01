package com.example.home.ui

import android.util.Log
import com.example.common.base.BaseViewModel
import com.example.domains.usecases.SearchRepositoriesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : BaseViewModel() {

    fun searchRepositories(query: String) {
        disposables.add(
            searchRepositoriesUseCase.getRepositories(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.i("테스트", result.toString())
                }, { error ->
                    Log.i("테스트", error.toString())
                })
        )
    }
}