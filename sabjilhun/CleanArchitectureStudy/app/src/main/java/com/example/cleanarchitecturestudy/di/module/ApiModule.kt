package com.example.cleanarchitecturestudy.di.module

import com.example.cleanarchitecturestudy.di.ApplicationScope
import com.example.data.remote.GithubApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class ApiModule {

    @Provides
    @ApplicationScope
    fun provideGithubApi(
        @Named("HeaderAdded") okHttpClient: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory
    ): GithubApi = Retrofit.Builder()
        .baseUrl(GITHUB_API_END_POINT)
        .client(okHttpClient)
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)
        .build()
        .create(GithubApi::class.java)

    @Provides
    @ApplicationScope
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides
    @ApplicationScope
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    companion object {
        private const val GITHUB_API_END_POINT = "https://api.github.com/"
    }
}