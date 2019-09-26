package com.beok.githubreposearch.custom

import android.app.Application
import com.beok.githubreposearch.di.dataSourceModule
import com.beok.githubreposearch.di.injectRemoteServiceModule
import com.beok.githubreposearch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(
                listOf(
                    viewModelModule,
                    dataSourceModule,
                    injectRemoteServiceModule("https://api.github.com/")
                )
            )
        }
    }
}