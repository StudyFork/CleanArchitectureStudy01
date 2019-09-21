package dev.daeyeon.githubsampleapp.module

import dev.daeyeon.githubsampleapp.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
}