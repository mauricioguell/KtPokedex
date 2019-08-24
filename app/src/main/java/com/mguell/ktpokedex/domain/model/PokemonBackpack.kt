package com.mguell.ktpokedex.domain.model

object PokemonBackpack {

    private val backpack: ArrayList<Pokemon> = ArrayList()

    fun savePokemon(pokemon: Pokemon) {
        backpack.add(pokemon)
    }

    fun getBackpack(): ArrayList<Pokemon> {
        return backpack
    }

    fun fillBackpack(pokemons: List<Pokemon>) {
        backpack.clear()
        backpack.addAll(pokemons)
    }
}