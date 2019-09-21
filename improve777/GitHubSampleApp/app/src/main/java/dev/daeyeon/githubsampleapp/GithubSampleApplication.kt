package dev.daeyeon.githubsampleapp

import android.app.Application
import dev.daeyeon.common.BuildConfig
import dev.daeyeon.common.ext.initKoin
import dev.daeyeon.common.module.createNetworkModule
import dev.daeyeon.githubsampleapp.module.*
import timber.log.Timber

class GithubSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        initKoin(
            applicationContext = this,
            isDebuggable = BuildConfig.DEBUG,
            modules = listOf(
                createNetworkModule("https://api.github.com/", BuildConfig.DEBUG),
                apiModule,
                persistenceModule,
                dataModule,
                useCaseModule,
                viewModelModule
            )
        )
    }
}