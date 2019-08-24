package com.mguell.ktpokedex.data.entity.remote

import com.google.gson.annotations.SerializedName

data class TypeNameEntity(
    @SerializedName("name")
    val name: String
)