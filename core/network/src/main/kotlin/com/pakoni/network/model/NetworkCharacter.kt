package com.pakoni.network.model

import com.google.gson.annotations.SerializedName
import com.pakoni.model.data.CharacterModel

data class NetworkCharacter(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String
){

    fun toPresentation(): CharacterModel {
        return CharacterModel(
            id = id,
            name = name,
            image = image,
            status = status,
            species = species,
        )
    }
}