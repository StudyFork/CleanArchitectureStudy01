package gong.team.githubclean.di

import gong.team.data.datasource.local.database.LocalDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val roomModule = module {
    single { LocalDataBase.getInstance(androidApplication())?.TokenDao() }
}