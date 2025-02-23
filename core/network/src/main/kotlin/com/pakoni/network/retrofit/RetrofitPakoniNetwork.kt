package com.pakoni.network.retrofit

import com.pakoni.network.model.NetworkCharacter
import com.pakoni.network.model.NetworkPallete
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitPakoniNetwork {

    @GET("/api/character/")
    suspend fun getCharacters(@Query("page") page: Int): NetworkCharacter

    @GET("/api/pallete/")
    suspend fun getPallete(@Query("page") page: Int): NetworkPallete
}