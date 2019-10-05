package dev.daeyeon.githubsampleapp.module

import dev.daeyeon.domain.usecase.GetReposUseCase
import dev.daeyeon.domain.usecase.SaveReposUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { GetReposUseCase(get()) }
    single { SaveReposUseCase(get()) }
}