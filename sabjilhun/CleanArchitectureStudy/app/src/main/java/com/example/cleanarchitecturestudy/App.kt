package com.example.cleanarchitecturestudy

import android.app.Application
import com.example.cleanarchitecturestudy.di.component.ApplicationComponent
import com.example.cleanarchitecturestudy.di.component.DaggerApplicationComponent
import com.example.common.di.SubComponentProvider

class App : Application(), SubComponentProvider {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        applicationComponent = DaggerApplicationComponent.factory().create(this)
        super.onCreate()
    }

    override fun getSubComponent() = applicationComponent.getAppSubComponent()
}