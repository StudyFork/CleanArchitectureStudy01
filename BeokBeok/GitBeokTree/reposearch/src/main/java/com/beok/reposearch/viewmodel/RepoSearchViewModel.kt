package com.beok.reposearch.viewmodel

import com.beok.common.base.BaseViewModel
import com.beok.reposearch.usecase.UserRepoSearchUseCase

class RepoSearchViewModel(
    private val userRepoSearchUseCase: UserRepoSearchUseCase
) : BaseViewModel()