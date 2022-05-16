package com.reza.samplelogin.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.reza.samplelogin.data.api.ApiService
import com.reza.samplelogin.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providerBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideConnectionTime() = Constants.CONNECTION_TIME

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun providerInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun providerClient(time: Long, interceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .readTimeout(time, TimeUnit.SECONDS)
        .writeTimeout(time, TimeUnit.SECONDS)
        .connectTimeout(time, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): ApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
}