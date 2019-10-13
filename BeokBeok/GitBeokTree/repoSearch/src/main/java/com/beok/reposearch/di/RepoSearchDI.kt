package com.beok.reposearch.di

import com.beok.reposearch.repository.RepoSearchRepository
import com.beok.reposearch.repository.data.RepoSearchDataSource
import com.beok.reposearch.repository.data.RepoSearchDataSourceImpl
import com.beok.reposearch.repository.service.RepoSearchService
import com.beok.reposearch.usecase.UserRepoSearchUsecase
import com.beok.reposearch.viewmodel.RepoSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object RepoSearchDI {

    private val retrofitModule = module {
        factory { get<Retrofit>().create(RepoSearchService::class.java) }
    }

    private val dataSourceModule = module {
        factory<RepoSearchDataSource>(named("repoSearchRepository")) { RepoSearchRepository(get()) }
        factory<RepoSearchDataSource> { RepoSearchDataSourceImpl(get()) }
    }

    private val usecaseModule = module {
        factory { UserRepoSearchUsecase(get(named("repoSearchRepository"))) }
    }

    private val viewModelModule = module {
        viewModel { RepoSearchViewModel(get()) }
    }

    val repoSearchModule = listOf(
        viewModelModule,
        usecaseModule,
        dataSourceModule,
        retrofitModule
    )
}