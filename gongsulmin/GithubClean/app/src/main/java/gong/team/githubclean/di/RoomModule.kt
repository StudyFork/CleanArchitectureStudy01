package gong.team.githubclean.di

import gong.team.data.datasource.local.database.LocalDataBase
import gong.team.data.datasource.local.database.LocalDataBaseImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val roomModule = module {
    single { LocalDataBaseImpl.getInstance(androidContext()) }
    single { (get() as LocalDataBase).TokenDao()}
}