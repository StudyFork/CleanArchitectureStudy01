package com.example.cleanarchitecturestudy.di.component

import android.content.Context
import com.example.cleanarchitecturestudy.di.annotation.ActivityScope
import com.example.cleanarchitecturestudy.di.annotation.ApplicationScope
import com.example.cleanarchitecturestudy.di.module.ApiModule
import com.example.cleanarchitecturestudy.di.module.ApplicationModule
import com.example.cleanarchitecturestudy.di.module.NetworkModule
import com.example.cleanarchitecturestudy.di.module.ViewModelModule
import com.example.common.di.AppSubComponent
import com.example.domains.usecases.GetRepositoryContentsInPathUseCase
import com.example.domains.usecases.GetRepositoryContentsInRootUseCase
import com.example.domains.usecases.SearchRepositoriesUseCase
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ViewModelModule::class,
        ApplicationModule::class,
        ApiModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent {

    fun searchRepositoriesUseCase(): SearchRepositoriesUseCase

    fun getRepositoryContentsInPathUseCase(): GetRepositoryContentsInPathUseCase

    fun getRepositoryContentsInRootUseCase(): GetRepositoryContentsInRootUseCase

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewModelModule::class]
)
interface ActivityComponent {

    fun getAppSubComponent(): AppSubComponent
}