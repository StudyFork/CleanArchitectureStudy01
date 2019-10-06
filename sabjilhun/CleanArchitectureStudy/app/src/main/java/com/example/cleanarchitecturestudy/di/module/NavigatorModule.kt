package com.example.cleanarchitecturestudy.di.module

import com.example.filetree.FileTreeNavigatorImpl
import com.example.navigator.FileTreeNavigator
import dagger.Binds
import dagger.Module

@Module
abstract class NavigatorModule {

    @Binds
    abstract fun bindFileNavigator(fileTreeNavigatorImpl: FileTreeNavigatorImpl): FileTreeNavigator
}