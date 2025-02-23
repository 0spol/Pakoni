package com.pakoni.network.model

import com.google.gson.annotations.SerializedName
import com.pakoni.data.model.Character
import com.pakoni.data.model.Pallete

data class ResponseWrapper(
    @SerializedName("info") val information:InfoResponse,
    @SerializedName("results") val results:List<Character>,
//    @SerializedName("pallete") val pallete: Pallete,
)