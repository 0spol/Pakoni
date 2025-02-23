package com.pakoni.network.model

import com.google.gson.annotations.SerializedName
import com.pakoni.data.model.Pallete

data class NetworkPallete(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("image") val image: String,
){
    fun toPresentation(): Pallete {
        return Pallete(
            id = id,
            name = name,
            image = image,
            isAlive = status == "Alive"
        )
    }
}