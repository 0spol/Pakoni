package com.pakoni.network.di

import com.pakoni.network.retrofit.RetrofitPakoniNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL_RICK = "https://rickandmortyapi.com/api/"
//    private const val BASE_URL_PALLETE = "https://rickandmortyapi.com/api/"

    @Provides
    fun provideApiService(retrofit: Retrofit): RetrofitPakoniNetwork =
        retrofit.create(RetrofitPakoniNetwork::class.java)

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_RICK)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()
}
