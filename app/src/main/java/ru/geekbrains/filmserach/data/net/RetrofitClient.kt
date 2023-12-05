package ru.geekbrains.filmserach.data.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

const val PATH = "https://api.kinopoisk.dev"

object RetrofitClient {
    private var client: Retrofit? = null

    fun getClient(): Retrofit {
        if (client == null) {

            client = Retrofit.Builder()
                .baseUrl(PATH)
                .client(client(FilmApiInterceptor()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return client!!
    }

    private fun client(interceptor: Interceptor): OkHttpClient {

        val builder = OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )

        return builder.build()
    }

    class FilmApiInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
        }
    }
}
