package com.beok.reposearch.di

import com.beok.reposearch.repository.RepoSearchRepository
import com.beok.reposearch.repository.data.RepoSearchDataSource
import com.beok.reposearch.repository.data.RepoSearchDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single { RepoSearchRepository(get()) }
    single<RepoSearchDataSource> { RepoSearchDataSourceImpl(get()) }
}