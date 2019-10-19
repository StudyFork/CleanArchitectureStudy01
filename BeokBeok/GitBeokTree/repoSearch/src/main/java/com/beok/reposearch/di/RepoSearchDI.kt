package com.beok.reposearch.di

import com.beok.reposearch.data.RepoSearchRepository
import com.beok.reposearch.data.RepoSearchService
import com.beok.reposearch.data.source.RepoSearchDataSource
import com.beok.reposearch.data.source.RepoSearchDataSourceImpl
import com.beok.reposearch.domain.usecase.UserRepoSearchUsecase
import com.beok.reposearch.presenter.RepoSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object RepoSearchDI {

    private val retrofitModule = module {
        factory { get<Retrofit>().create(RepoSearchService::class.java) }
    }

    private val dataSourceModule = module {
        factory<RepoSearchDataSource>(named("repoSearchRepository")) {
            RepoSearchRepository(
                get()
            )
        }
        factory<RepoSearchDataSource> {
            RepoSearchDataSourceImpl(
                get()
            )
        }
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