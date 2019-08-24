package com.mguell.ktpokedex.deps

import com.mguell.ktpokedex.presentation.backpack.BackpackFragment
import com.mguell.ktpokedex.presentation.details.DetailsFragment
import com.mguell.ktpokedex.presentation.nointernet.NoInternetFragment
import com.mguell.ktpokedex.presentation.searchpokemon.SearchPokemonFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [BackpackFragmentModule::class])
    abstract fun backpackFragment(): BackpackFragment

    @ContributesAndroidInjector(modules = [SearchPokemonFragmentModule::class])
    abstract fun searchedPokemonFragment(): SearchPokemonFragment

    @ContributesAndroidInjector(modules = [NoInternetFragmentModule::class])
    abstract fun noInternetFragment(): NoInternetFragment

    @ContributesAndroidInjector(modules = [DetailsFragmentModule::class])
    abstract fun detailFragment(): DetailsFragment
}
