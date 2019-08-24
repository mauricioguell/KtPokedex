package com.mguell.ktpokedex.data.repository.datasource

import com.mguell.ktpokedex.data.entity.remote.PokemonEntity
import com.mguell.ktpokedex.data.net.RestClient
import io.reactivex.Observable

class PokemonDataStore : IPokemonDataStore {

    override fun pokemonById(id: Int): Observable<PokemonEntity> {
        return RestClient.pokemonService.pokemonById(id)
    }
}