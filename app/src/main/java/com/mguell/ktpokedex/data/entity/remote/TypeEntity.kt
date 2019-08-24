package com.mguell.ktpokedex.data.entity.remote

import com.google.gson.annotations.SerializedName

data class TypeEntity(
    @SerializedName("type")
    val typeName: TypeNameEntity
)