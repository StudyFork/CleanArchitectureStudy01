package com.example.cleanarchitecturestudy.di.module

import com.example.cleanarchitecturestudy.di.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    @Named("HeaderAdded")
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("AcceptHeaderAddingInterceptor") githubAcceptHeaderAddingInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(githubAcceptHeaderAddingInterceptor)
        .build()

    @Provides
    @ApplicationScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @ApplicationScope
    @Named("AcceptHeaderAddingInterceptor")
    fun provideGithubAcceptHeaderAddingInterceptor(): Interceptor =
        Interceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("Accept", GITHUB_ACCEPT_HEADER)
                    .build()
            )
        }

    companion object {
        const val GITHUB_ACCEPT_HEADER = "application/vnd.github.v3+json"
    }
}