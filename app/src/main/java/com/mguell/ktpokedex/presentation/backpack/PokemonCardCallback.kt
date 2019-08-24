package com.mguell.ktpokedex.presentation.backpack

import com.mguell.ktpokedex.domain.model.Pokemon

interface PokemonCardCallback {

    fun selectedPokemon(pokemon: Pokemon)
}