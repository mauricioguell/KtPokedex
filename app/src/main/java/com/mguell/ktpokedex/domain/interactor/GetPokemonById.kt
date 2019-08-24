package com.mguell.ktpokedex.domain.interactor

import com.mguell.ktpokedex.domain.model.Pokemon
import com.mguell.ktpokedex.domain.repository.PokemonRepository
import io.reactivex.Observable

class GetPokemonById(private val pokemonRepository: PokemonRepository) : UseCase<Pokemon, Int>() {

    override fun buildUseCaseObservable(params: Int): Observable<Pokemon> {
        return pokemonRepository.pokemonById(params)
    }
}
