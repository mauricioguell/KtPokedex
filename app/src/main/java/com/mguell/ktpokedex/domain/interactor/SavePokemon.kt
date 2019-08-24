package com.mguell.ktpokedex.domain.interactor

import com.mguell.ktpokedex.domain.model.Pokemon
import com.mguell.ktpokedex.domain.model.PokemonBackpack
import com.mguell.ktpokedex.domain.repository.PokemonRepository
import io.reactivex.Observable

class SavePokemon(private val pokemonRepository: PokemonRepository) : UseCase<Void, Pokemon>() {

    override fun buildUseCaseObservable(params: Pokemon): Observable<Void> {
        PokemonBackpack.savePokemon(params)
        return pokemonRepository.savePokemon(params)
    }
}
