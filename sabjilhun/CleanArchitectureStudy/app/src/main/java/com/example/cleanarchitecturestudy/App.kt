package com.example.cleanarchitecturestudy

import android.app.Activity
import android.app.Application
import com.example.cleanarchitecturestudy.di.ApplicationComponent
import com.example.cleanarchitecturestudy.di.DaggerApplicationComponent
import com.example.cleanarchitecturestudy.di.InjectorImpl
import com.example.common.Injector

class App : Application(), Injector.Provider {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        applicationComponent = DaggerApplicationComponent.factory().create(this)
        super.onCreate()
    }

    override fun provide(activity: Activity): Injector =
        InjectorImpl(applicationComponent.activityComponentFactory.create(activity))
}