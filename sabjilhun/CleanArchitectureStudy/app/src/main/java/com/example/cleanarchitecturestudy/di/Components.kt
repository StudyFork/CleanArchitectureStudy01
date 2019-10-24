package com.example.cleanarchitecturestudy.di

import android.app.Activity
import android.content.Context
import com.example.cleanarchitecturestudy.di.module.*
import com.example.common.base.InjectActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@ApplicationScope
@Component(
    modules = [
        ApplicationModule::class,
        ApiModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent {

    val activityComponentFactory: ActivityComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}

@ActivityScope
@Subcomponent(
    modules = [
        ViewModelModule::class,
        NavigatorModule::class
    ]
)
interface ActivityComponent {

    fun inject(injectActivity: InjectActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): ActivityComponent
    }
}