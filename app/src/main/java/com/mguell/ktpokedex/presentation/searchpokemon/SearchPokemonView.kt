package com.mguell.ktpokedex.presentation.searchpokemon

import com.mguell.ktpokedex.domain.model.Pokemon

interface SearchPokemonView {

    fun setLoadingPokemonBarVisibility(visibility: Int)
    fun setFreePokemon(pokemon: Pokemon)
    fun noPokemonFound()
    fun savePokemonSuccess()
    fun connectionError()
    fun setCaughtPokemon(pokemon: Pokemon)
}