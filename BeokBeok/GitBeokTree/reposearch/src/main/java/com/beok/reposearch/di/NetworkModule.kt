package com.beok.reposearch.di

import com.beok.reposearch.repository.service.RepoSearchService
import org.koin.dsl.module
import retrofit2.Retrofit

val retrofitModule = module {
    single { get<Retrofit>().create(RepoSearchService::class.java) }
}