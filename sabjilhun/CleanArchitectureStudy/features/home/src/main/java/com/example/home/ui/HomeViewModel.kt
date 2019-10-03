package com.example.home.ui

import com.example.common.base.BaseViewModel
import com.example.domains.usecases.SearchRepositoriesUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : BaseViewModel() {

    fun test() {
        searchRepositoriesUseCase.getRepositories()
    }
}