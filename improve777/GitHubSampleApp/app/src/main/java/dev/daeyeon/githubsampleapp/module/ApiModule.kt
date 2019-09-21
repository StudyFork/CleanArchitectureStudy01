package dev.daeyeon.githubsampleapp.module

import dev.daeyeon.data.remote.api.GithubApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    single {
        (get() as Retrofit).create(GithubApi::class.java)
    }
}