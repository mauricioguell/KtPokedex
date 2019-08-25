package com.mguell.ktpokedex.domain.model

object PokemonBackpack {

    private val backpack: ArrayList<Pokemon> = ArrayList()

    fun getBackpack(): ArrayList<Pokemon> {
        return backpack
    }

    fun fillBackpack(pokemons: List<Pokemon>) {
        backpack.clear()
        backpack.addAll(pokemons)
    }
}