package com.pakoni.network.di

import com.pakoni.network.retrofit.RetrofitPakoniNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL_RICK = "https://rickandmortyapi.com/api/"
//    private const val BASE_URL_PALLETE = "https://rickandmortyapi.com/api/"

    // Proveer la instancia de RetrofitPakoniNetwork (interfaz de Retrofit)
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RetrofitPakoniNetwork =
        retrofit.create(RetrofitPakoniNetwork::class.java)

    // Proveer la instancia de Retrofit con OkHttpClient
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_RICK)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    // Proveer la instancia de OkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()
}

