package com.example.cleanarchitecturestudy.di

import com.example.common.Injector
import com.example.common.base.InjectActivity


class InjectorImpl constructor(
    private val activityComponent: ActivityComponent
) : Injector {

    override fun inject(injectActivity: InjectActivity) {
        activityComponent.inject(injectActivity)
    }
}