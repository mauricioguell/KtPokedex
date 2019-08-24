package com.mguell.ktpokedex.deps

import com.mguell.ktpokedex.domain.interactor.GetPokemonById
import com.mguell.ktpokedex.domain.interactor.SavePokemon
import com.mguell.ktpokedex.domain.repository.PokemonRepository
import com.mguell.ktpokedex.presentation.searchpokemon.SearchPokemonPresenter
import dagger.Module
import dagger.Provides

@Module
class SearchPokemonFragmentModule {

    @Provides
    fun provideSearchedPokemonPresenter(
        getPokemonById: GetPokemonById,
        savePokemon: SavePokemon
    ): SearchPokemonPresenter {
        return SearchPokemonPresenter(getPokemonById, savePokemon)
    }

    @Provides
    fun provideGetPokemonById(pokemonRepository: PokemonRepository): GetPokemonById {
        return GetPokemonById(pokemonRepository)
    }

    @Provides
    fun provideSavePokemon(pokemonRepository: PokemonRepository): SavePokemon {
        return SavePokemon(pokemonRepository)
    }
}