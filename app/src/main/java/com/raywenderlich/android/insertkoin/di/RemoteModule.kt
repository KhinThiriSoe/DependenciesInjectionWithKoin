package com.raywenderlich.android.insertkoin.di

import com.raywenderlich.android.insertkoin.di.RemoteProperties.BASE_URL
import com.raywenderlich.android.insertkoin.repository.RemoteDataSource
import org.koin.dsl.module.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteProperties {
    const val BASE_URL = "BASE_URL"
}

fun createBaseUrl() = "https://api.github.com/"

fun createConverterFactory(): Converter.Factory {
    return GsonConverterFactory.create()
}

inline fun <reified T> createWebService(baseUrl: String, factory: Converter.Factory): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(factory)
            .build()
    return retrofit.create(T::class.java)
}

val remoteModule = module {
    single(BASE_URL) { createBaseUrl() }
    single { createConverterFactory() }
    single { createWebService<RemoteDataSource>(get(BASE_URL), get()) }
}