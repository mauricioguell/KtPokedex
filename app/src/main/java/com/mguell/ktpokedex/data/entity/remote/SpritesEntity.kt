package com.mguell.ktpokedex.data.entity.remote

import com.google.gson.annotations.SerializedName

data class SpritesEntity(
    @SerializedName("front_default")
    val frontDefaultPath: String
)