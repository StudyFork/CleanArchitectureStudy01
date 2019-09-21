package dev.daeyeon.githubsampleapp.module

import dev.daeyeon.data.local.LocalDataBase
import dev.daeyeon.data.local.LocalDataBaseProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val persistenceModule = module {

    single { LocalDataBaseProvider.getInstance(androidContext()) }

    single { (get() as LocalDataBase).repoDao() }
}
