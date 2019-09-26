package com.beok.githubreposearch.di

import com.beok.githubreposearch.reposearch.RepoSearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RepoSearchViewModel(get()) }
}