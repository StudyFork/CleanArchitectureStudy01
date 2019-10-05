package com.beok.reposearch.di

import com.beok.reposearch.usecase.UserRepoSearchUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { UserRepoSearchUseCase(get()) }
}