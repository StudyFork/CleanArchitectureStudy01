package com.beok.fileviewer.di

import com.beok.fileviewer.presenter.FileViewerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FileViewerDI {

    private val viewModelModule = module {
        viewModel { FileViewerViewModel() }
    }

    val fileViewModules = listOf(viewModelModule)
}