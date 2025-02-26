package com.pakoni.network.model

import com.google.gson.annotations.SerializedName
import com.pakoni.model.data.PalleteModel

data class NetworkPallete(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("image") val image: String,
){
    fun toPresentation(): PalleteModel {
        return PalleteModel(
            id = id,
            name = name,
            image = image,
            status = status == "Alive"
        )
    }
}