package com.beok.repobrowse.di

import com.beok.repobrowse.repository.RepoBrowseRepository
import com.beok.repobrowse.repository.data.RepoBrowseDataSource
import com.beok.repobrowse.repository.data.RepoBrowseDataSourceImpl
import com.beok.repobrowse.repository.service.RepoBrowseService
import com.beok.repobrowse.usecase.UserRepoBrowseUsecase
import com.beok.repobrowse.viewmodel.RepoBrowseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object RepoBrowseDI {

    private val retrofitModule = module {
        factory { get<Retrofit>().create(RepoBrowseService::class.java) }
    }

    private val dataSourceModule = module {
        factory<RepoBrowseDataSource>(named("repoBrowseRepository")) { RepoBrowseRepository(get()) }
        factory<RepoBrowseDataSource> { RepoBrowseDataSourceImpl(get()) }
    }

    private val usecaseModule = module {
        factory { UserRepoBrowseUsecase(get(named("repoBrowseRepository"))) }
    }

    private val viewModelModule = module {
        viewModel { RepoBrowseViewModel(get()) }
    }

    val repoBrowseModule = listOf(
        viewModelModule,
        usecaseModule,
        dataSourceModule,
        retrofitModule
    )
}