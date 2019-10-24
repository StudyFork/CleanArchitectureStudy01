package com.example.common

import android.app.Activity
import com.example.common.base.InjectActivity

interface Injector {
    fun inject(injectActivity: InjectActivity)

    interface Provider {
        fun provide(activity: Activity): Injector
    }
}