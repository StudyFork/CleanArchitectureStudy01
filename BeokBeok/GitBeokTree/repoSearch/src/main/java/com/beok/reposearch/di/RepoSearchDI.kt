package com.beok.reposearch.di

import com.beok.reposearch.repository.RepoSearchRepository
import com.beok.reposearch.repository.data.RepoSearchDataSource
import com.beok.reposearch.repository.data.RepoSearchDataSourceImpl
import com.beok.reposearch.repository.service.RepoSearchService
import com.beok.reposearch.usecase.UserRepoSearchUseCase
import com.beok.reposearch.viewmodel.RepoSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object RepoSearchDI {

    private val retrofitModule = module {
        single { get<Retrofit>().create(RepoSearchService::class.java) }
    }

    private val dataSourceModule = module {
        single<RepoSearchDataSource>(named("repoSearchRepository")) { RepoSearchRepository(get()) }
        single<RepoSearchDataSource> { RepoSearchDataSourceImpl(get()) }
    }

    private val useCaseModule = module {
        single { UserRepoSearchUseCase(get(named("repoSearchRepository"))) }
    }

    private val viewModelModule = module {
        viewModel { RepoSearchViewModel(get()) }
    }

    val repoSearchModule = listOf(
        viewModelModule,
        useCaseModule,
        dataSourceModule,
        retrofitModule
    )
}