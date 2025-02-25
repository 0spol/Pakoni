package com.pakoni.network.model

import com.google.gson.annotations.SerializedName

data class ResponseWrapper(
    @SerializedName("info") val information:NetworkInfoResponse,
    @SerializedName("results") val results:List<NetworkCharacter>,
//    @SerializedName("pallete") val pallete: Pallete,
)