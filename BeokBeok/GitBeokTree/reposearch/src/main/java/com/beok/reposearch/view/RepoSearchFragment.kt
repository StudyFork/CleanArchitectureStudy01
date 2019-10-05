package com.beok.reposearch.view

import com.beok.common.base.BaseFragment
import com.beok.reposearch.R
import com.beok.reposearch.databinding.FragmentRepoSearchBinding
import com.beok.reposearch.viewmodel.RepoSearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RepoSearchFragment : BaseFragment<FragmentRepoSearchBinding, RepoSearchViewModel>(
    R.layout.fragment_repo_search
) {
    override val viewModel by viewModel<RepoSearchViewModel>()
}