package com.beok.githubreposearch.di

import com.beok.githubreposearch.data.RepoSearchRepository
import com.beok.githubreposearch.data.remote.RepoSearchRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { RepoSearchRepository(get()) }
    single { RepoSearchRemoteDataSource(get()) }
}