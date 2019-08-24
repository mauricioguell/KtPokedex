package com.mguell.ktpokedex.domain.repository

import com.mguell.ktpokedex.domain.model.Pokemon
import io.reactivex.Observable

interface PokemonRepository {

    fun savePokemon(pokemon: Pokemon): Observable<Void>

    fun pokemonBackpack(): Observable<List<Pokemon>>

    fun pokemonById(
        id: Int
    ): Observable<Pokemon>
}