package com.mguell.ktpokedex.data.repository.datasource

import com.mguell.ktpokedex.data.entity.remote.PokemonEntity
import io.reactivex.Observable

interface IPokemonDataStore {

    fun pokemonById(
        id: Int
    ): Observable<PokemonEntity>
}