package com.mguell.ktpokedex.domain.model

import java.io.Serializable

data class Pokemon(
    val name: String,
    val height: Int,
    val weight: Int,
    val id: Int,
    val posterPath: String,
    val order: Int,
    val baseExperience: Int,
    val types: ArrayList<String>,
    var catchedTime: String
) : Serializable {

    override fun hashCode(): Int {
        return id
    }

    override fun equals(other: Any?): Boolean {
        return other is Pokemon && other.id == id
    }
}