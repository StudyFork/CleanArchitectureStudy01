package com.beok.repobrowse.di

import com.beok.repobrowse.viewmodel.RepoBrowseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object RepoBrowseDI {

    private val viewModelModule = module {
        viewModel { RepoBrowseViewModel() }
    }

    val repoBrowseModule = listOf(
        viewModelModule
    )
}