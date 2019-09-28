package com.example.cleanarchitecturestudy.di.component

import android.content.Context
import com.example.cleanarchitecturestudy.di.module.ViewModelModule
import com.example.common.di.AppSubComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface ApplicationComponent {

    fun getAppSubComponent(): AppSubComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}