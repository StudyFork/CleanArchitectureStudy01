package com.beok.reposearch.di

import com.beok.reposearch.usecase.UserRepoSearchUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    single { UserRepoSearchUseCase(get(named("repo"))) }
}