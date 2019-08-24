package com.mguell.ktpokedex.data.entity.remote

import com.google.gson.annotations.SerializedName

data class PokemonEntity(
    @SerializedName("name")
    val name: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sprites")
    val sprites: SpritesEntity,
    @SerializedName("order")
    val order: Int,
    @SerializedName("base_experience")
    val baseExperience: Int,
    @SerializedName("types")
    val types: ArrayList<TypeEntity>
)