package com.beok.githubreposearch.di

import com.beok.githubreposearch.BuildConfig
import com.beok.githubreposearch.data.remote.RepoSearchRemoteService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun injectRemoteServiceModule(url: String) = module {
    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single { GsonConverterFactory.create() }
    single {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get<OkHttpClient>())
            .build()
    }
    single { get<Retrofit>().create(RepoSearchRemoteService::class.java) }
}