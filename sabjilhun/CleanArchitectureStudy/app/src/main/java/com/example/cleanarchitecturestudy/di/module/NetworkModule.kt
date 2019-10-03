package com.example.cleanarchitecturestudy.di.module

import com.example.cleanarchitecturestudy.di.annotation.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    @Named("HeaderAdded")
    internal fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @ApplicationScope
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
//
//    @Provides
//    @Singleton
//    @Named("HeaderAddingInterceptor")
//    internal fun provideHeaderAddingInterceptor(): Interceptor {
//        return { chain ->
//            chain.proceed(
//                chain.request()
//                    .newBuilder()
//                    .addHeader("Authorization", HEADER_KEY)
//                    .build()
//            )
//        }
//    }
}