package com.pakoni.network.model

import com.google.gson.annotations.SerializedName
import com.pakoni.data.model.Character

data class NetworkCharacter(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("image") val image: String,
){
    fun toPresentation():Character{
        return Character(
            id = id,
            name = name,
            image = image,
            isAlive = status == "Alive"
        )
    }
}