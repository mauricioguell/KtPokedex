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

    /**
     * Comparing everything but catched time. Bulbasaur is Bulbasaur whether its wild
     * or caught.
     */
    override fun equals(other: Any?): Boolean {
        return other is Pokemon
                && other.name == name
                && other.height == height
                && other.weight == weight
                && other.id == id
                && other.posterPath == posterPath
                && other.order == order
                && other.baseExperience == baseExperience
                && other.types == types
    }
}