package com.beok.githubreposearch.di

import com.beok.githubreposearch.data.domain.RepoSearchUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { RepoSearchUseCase(get()) }
}