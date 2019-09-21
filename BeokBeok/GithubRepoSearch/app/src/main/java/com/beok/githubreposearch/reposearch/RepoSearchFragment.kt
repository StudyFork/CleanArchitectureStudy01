package com.beok.githubreposearch.reposearch


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.beok.githubreposearch.R
import com.beok.githubreposearch.base.BaseFragment
import com.beok.githubreposearch.data.RepoSearchRepository
import com.beok.githubreposearch.data.remote.RepoSearchRemoteDataSource
import com.beok.githubreposearch.data.remote.RepoSearchRetrofit
import com.beok.githubreposearch.databinding.FragmentRepoSearchBinding

class RepoSearchFragment : BaseFragment<FragmentRepoSearchBinding, RepoSearchViewModel>(
    R.layout.fragment_repo_search
) {

    override val viewModel by lazy {
        ViewModelProviders.of(
            this,
            object : ViewModelProvider.Factory {

                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    RepoSearchViewModel(
                        RepoSearchRepository(
                            RepoSearchRemoteDataSource(RepoSearchRetrofit.service)
                        )
                    ) as T
            }
        )[RepoSearchViewModel::class.java]
    }

}

