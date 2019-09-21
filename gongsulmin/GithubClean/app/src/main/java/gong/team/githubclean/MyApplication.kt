package gong.team.githubclean

import android.app.Application
import gong.team.githubclean.di.*
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG){
                androidLogger()
            }
            modules(
                listOf(
                    networkModule  ,
                    repositoryModule ,
                    mapperModule ,
                    viewmodelModule ,
                    userCaseModule
                )
            )
        }
    }
}