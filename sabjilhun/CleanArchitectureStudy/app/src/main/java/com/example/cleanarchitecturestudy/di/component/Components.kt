package com.example.cleanarchitecturestudy.di.component

import android.content.Context
import com.example.cleanarchitecturestudy.di.annotation.ActivityScope
import com.example.cleanarchitecturestudy.di.annotation.ApplicationScope
import com.example.cleanarchitecturestudy.di.module.ViewModelModule
import com.example.common.di.AppSubComponent
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ViewModelModule::class])
interface ApplicationComponent {

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