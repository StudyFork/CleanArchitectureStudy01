package dev.daeyeon.common.module

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun createNetworkModule(uri: String, isDebug: Boolean) = module {

    single<Retrofit> { createRetrofit(uri, isDebug) }
}

private fun createRetrofit(uri: String, isDebug: Boolean) =
    Retrofit.Builder()
        .baseUrl(uri)
        .client(createClient(isDebug))
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

private fun createClient(isDebug: Boolean) =
    OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)                       // 연결 시간초과
        .readTimeout(30, TimeUnit.SECONDS)                          // 읽기 시간초과
        .writeTimeout(15, TimeUnit.SECONDS)                         // 쓰기 시간초과
        .addInterceptor(createHttpLoggingInterceptor(isDebug))
        .build()

private fun createHttpLoggingInterceptor(isDebug: Boolean) =
    HttpLoggingInterceptor().apply {
        if (isDebug) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
