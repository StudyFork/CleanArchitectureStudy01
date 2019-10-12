package gong.team.data

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GithubApiFactory {

    // DI 작업
    inline fun <reified T> providerGithubApi(baseUrl: String ): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(providerOkhttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }

    fun providerOkhttpClient(): OkHttpClient  {
        return OkHttpClient.Builder().apply {
            addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }.build()
    }

    class AuthInterceptor(private val user: String , private val password: String): Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
                .newBuilder()
                .header("Authorization" , Credentials.basic(user , password))
                .build()
            return chain.proceed(request)
        }
    }

}


