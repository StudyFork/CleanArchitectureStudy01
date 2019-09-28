package com.example.common.di

import com.example.common.base.InjectActivity
import dagger.Subcomponent

interface SubComponentProvider {
    fun getSubComponent(): AppSubComponent
}

@Subcomponent
interface AppSubComponent {
    fun inject(injectActivity: InjectActivity)
}