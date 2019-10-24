package com.example.filetree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.base.BaseViewModel
import com.example.domain.datastructure.Tree
import com.example.domain.entities.RepositoryBranch
import com.example.domain.entities.RepositoryFile
import com.example.domain.usecases.GetRepositoryBranchListUseCase
import com.example.domain.usecases.GetRepositoryTreeUseCase
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class FileTreeViewModel @Inject constructor(
    private val getRepositoryFileTreeUseCase: GetRepositoryTreeUseCase,
    private val getRepositoryBranchListUseCase: GetRepositoryBranchListUseCase
) : BaseViewModel() {

    private val _title: MutableLiveData<Pair<String, String>> = MutableLiveData()
    val title: LiveData<Pair<String, String>> = _title

    private val _fileTree: MutableLiveData<Tree<RepositoryFile>> = MutableLiveData()
    val fileTree: LiveData<Tree<RepositoryFile>> = _fileTree

    private val _repositoryBranch: MutableLiveData<List<RepositoryBranch>> = MutableLiveData()
    val repositoryBranch: LiveData<List<RepositoryBranch>> = _repositoryBranch

    fun setRepoTitle(owner: String, repositoryName: String) {
        _title.value = Pair(owner, repositoryName)
    }

    fun getRepositoryFileTree(
        owner: String,
        repositoryName: String,
        branch: RepositoryBranch
    ) {
        getRepositoryFileTreeUseCase(owner, repositoryName, branch)
            .compose(apiLoadingTransformer())
            .subscribeBy(
                onSuccess = _fileTree::setValue,
                onError = ::handleApiErrorMessage
            ).add()
    }

    fun getRepositoryBranchList(
        owner: String,
        repositoryName: String
    ) {
        this.getRepositoryBranchListUseCase(owner, repositoryName)
            .compose(apiLoadingTransformer())
            .subscribeBy(
                onSuccess = _repositoryBranch::setValue,
                onError = ::handleApiErrorMessage
            ).add()
    }
}