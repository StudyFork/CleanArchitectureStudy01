package com.example.filetree

import com.example.common.base.BaseViewModel
import com.example.domain.usecases.GetRepositoryContentsInPathUseCase
import com.example.domain.usecases.GetRepositoryContentsInRootUseCase
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class FileTreeViewModel @Inject constructor(
    private val getRepositoryContentsInRootUseCase: GetRepositoryContentsInRootUseCase,
    private val getRepositoryContentsInPathUseCase: GetRepositoryContentsInPathUseCase
) : BaseViewModel() {

    fun getContentsInRoot(
        owner: String,
        repositoryName: String
    ) {
        getRepositoryContentsInRootUseCase(owner, repositoryName)
            .compose(apiLoadingTransformer())
            .subscribeBy(
                onSuccess = {},
                onError = {}
            ).add()
    }

    fun getContentsInPath(
        owner: String,
        repositoryName: String,
        path: String
    ) {
        getRepositoryContentsInPathUseCase(owner, repositoryName, path)
            .compose(apiLoadingTransformer())
            .subscribeBy(
                onSuccess = {},
                onError = {}
            ).add()
    }
}