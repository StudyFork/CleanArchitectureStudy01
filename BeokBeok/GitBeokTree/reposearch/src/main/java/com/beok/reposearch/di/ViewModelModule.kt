package com.beok.reposearch.di

import com.beok.reposearch.viewmodel.RepoSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RepoSearchViewModel(get()) }
}