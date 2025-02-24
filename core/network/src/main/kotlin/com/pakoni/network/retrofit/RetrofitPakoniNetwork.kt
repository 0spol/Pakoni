package com.pakoni.network.retrofit

import com.pakoni.network.model.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitPakoniNetwork {

    @GET("/api/character/")
    suspend fun getCharacters(@Query("page") page: Int): ResponseWrapper

}