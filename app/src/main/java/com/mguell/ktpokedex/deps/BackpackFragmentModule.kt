package com.mguell.ktpokedex.deps

import com.mguell.ktpokedex.domain.interactor.GetPokemonBackpack
import com.mguell.ktpokedex.domain.repository.PokemonRepository
import com.mguell.ktpokedex.presentation.backpack.BackpackPresenter
import dagger.Module
import dagger.Provides


@Module
class BackpackFragmentModule {

    @Provides
    fun provideBackpackPresenter(
        getPokemonBackpack: GetPokemonBackpack
    ): BackpackPresenter {
        return BackpackPresenter(getPokemonBackpack)
    }

    @Provides
    fun provideGetPokemonBackpack(pokemonRepository: PokemonRepository): GetPokemonBackpack {
        return GetPokemonBackpack(pokemonRepository)
    }
}