package com.beok.gitbeoktree

import android.app.Application
import com.beok.common.di.getRetrofitBasicModule
import com.beok.reposearch.di.dataSourceModule
import com.beok.reposearch.di.retrofitModule
import com.beok.reposearch.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

@Suppress("unused")
class Beokplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Beokplication)
            module {
                listOf(
                    useCaseModule,
                    dataSourceModule,
                    retrofitModule,
                    getRetrofitBasicModule("https://api.github.com/")
                )
            }
        }
    }
}