package com.beok.gitbeoktree

import android.app.Application
import com.beok.common.di.getRetrofitBasicModule
import com.beok.reposearch.di.RepoSearchDI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

@Suppress("unused")
class Beokplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Beokplication)
            modules(getKoinModules())
        }
    }

    private fun getKoinModules(): List<Module> {
        val koinModules = mutableListOf<Module>()
        RepoSearchDI.repoSearchModule.forEach { module ->
            koinModules.add(module)
        }
        koinModules.add(getRetrofitBasicModule("https://api.github.com/"))
        return koinModules
    }
}