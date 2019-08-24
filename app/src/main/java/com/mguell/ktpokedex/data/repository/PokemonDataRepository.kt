package com.mguell.ktpokedex.data.repository

import com.mguell.ktpokedex.data.entity.PokemonMapper
import com.mguell.ktpokedex.data.repository.database.PokemonDatabase
import com.mguell.ktpokedex.data.repository.datasource.IPokemonDataStore
import com.mguell.ktpokedex.domain.model.Pokemon
import com.mguell.ktpokedex.domain.repository.PokemonRepository
import io.reactivex.Observable

class PokemonDataRepository(
    private val pokemonDataStore: IPokemonDataStore,
    private val pokemonDatabase: PokemonDatabase
) : PokemonRepository {

    private val pokemonMapper: PokemonMapper =
        PokemonMapper()

    override fun savePokemon(pokemon: Pokemon): Observable<Void> {
        pokemonDatabase.savePokemon(pokemonMapper.transform(pokemon))
        return Observable.empty()
    }

    override fun pokemonBackpack(): Observable<List<Pokemon>> {
        return pokemonDatabase.getBackpack().map(pokemonMapper::transformRealmList)
    }

    override fun pokemonById(id: Int): Observable<Pokemon> {
        return pokemonDataStore.pokemonById(id).map(pokemonMapper::transform)
    }
}