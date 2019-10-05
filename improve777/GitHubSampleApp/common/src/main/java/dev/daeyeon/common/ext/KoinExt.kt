package dev.daeyeon.common.ext

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

fun initKoin(
    applicationContext: Application,
    isDebuggable: Boolean,
    modules: List<Module>
) {
    startKoin {
        androidLogger(if (isDebuggable) Level.DEBUG else Level.INFO)
        androidContext(applicationContext)
        androidFileProperties()
        modules(modules)
    }
}