package com.example.filetree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.base.BaseViewModel
import com.example.domain.datastructure.Node
import com.example.domain.datastructure.Tree
import com.example.domain.entities.RepositoryFile
import com.example.domain.usecases.GetRepositoryContentsInPathUseCase
import com.example.domain.usecases.GetRepositoryContentsInRootUseCase
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class FileTreeViewModel @Inject constructor(
    private val getRepositoryContentsInRootUseCase: GetRepositoryContentsInRootUseCase,
    private val getRepositoryContentsInPathUseCase: GetRepositoryContentsInPathUseCase
) : BaseViewModel() {

    private val _title: MutableLiveData<Pair<String, String>> = MutableLiveData()
    val title: LiveData<Pair<String, String>> = _title

    private val _nodeFileTreePair: MutableLiveData<Pair<Node<RepositoryFile>, Tree<RepositoryFile>>> =
        MutableLiveData()
    val fileTree: LiveData<Pair<Node<RepositoryFile>, Tree<RepositoryFile>>> = _nodeFileTreePair

    init {
        _nodeFileTreePair.value = Tree<RepositoryFile>().run { Pair(root, this) }
    }

    fun setRepoTitle(owner: String, repositoryName: String) {
        _title.value = Pair(owner, repositoryName)
    }

    fun getContentsInRoot(
        owner: String,
        repositoryName: String
    ) {
        getRepositoryContentsInRootUseCase(owner, repositoryName)
            .compose(apiLoadingTransformer())
            .subscribeBy(
                onSuccess = {
                    val fileTree = (_nodeFileTreePair.value ?: throw IllegalStateException()).second
                    fileTree.root.replaceAll(it.map(::Node))
                    _nodeFileTreePair.value = Pair(fileTree.root, fileTree)
                },
                onError = ::handleApiErrorMessage
            ).add()
    }

    fun getContentsInPath(
        owner: String,
        repositoryName: String,
        fileNode: Node<RepositoryFile>
    ) {
        val fileTree = (_nodeFileTreePair.value ?: throw IllegalStateException()).second

        getRepositoryContentsInPathUseCase(owner, repositoryName, fileNode, fileTree)
            .compose(apiLoadingTransformer())
            .subscribeBy(
                onSuccess = {
                    fileTree.setChildNodes(fileNode, it.map(::Node))
                    _nodeFileTreePair.value = Pair(fileNode, fileTree)
                },
                onError = ::handleApiErrorMessage
            ).add()
    }
}