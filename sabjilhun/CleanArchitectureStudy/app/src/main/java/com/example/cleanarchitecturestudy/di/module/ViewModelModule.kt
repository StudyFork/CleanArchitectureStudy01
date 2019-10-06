package com.example.cleanarchitecturestudy.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecturestudy.di.ActivityScope
import com.example.cleanarchitecturestudy.di.ViewModelKey
import com.example.cleanarchitecturestudy.di.factory.ViewModelFactory
import com.example.filetree.FileTreeViewModel
import com.example.search.ui.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @ActivityScope
    @Binds
    abstract fun bindFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FileTreeViewModel::class)
    abstract fun bindFileTreeViewModel(viewModel: FileTreeViewModel): ViewModel
}