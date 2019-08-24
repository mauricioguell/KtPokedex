package com.mguell.ktpokedex.domain.interactor

import com.mguell.ktpokedex.domain.model.Pokemon
import com.mguell.ktpokedex.domain.repository.PokemonRepository
import io.reactivex.Observable

class GetPokemonBackpack(private val pokemonRepository: PokemonRepository) :
    UseCase<List<Pokemon>, GetPokemonBackpack.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<List<Pokemon>> {
        return this.pokemonRepository.pokemonBackpack()
    }

    class Params
}