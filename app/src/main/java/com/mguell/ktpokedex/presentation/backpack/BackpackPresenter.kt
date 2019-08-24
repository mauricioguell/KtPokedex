package com.mguell.ktpokedex.presentation.backpack

import android.util.Log
import android.view.View
import com.mguell.ktpokedex.domain.interactor.GetPokemonBackpack
import com.mguell.ktpokedex.domain.model.Pokemon
import com.mguell.ktpokedex.domain.model.PokemonBackpack
import io.reactivex.observers.DisposableObserver

class BackpackPresenter(private val getPokemonBackpack: GetPokemonBackpack) {

    companion object {
        private val TAG = this::class.java.simpleName
    }

    private lateinit var view: BackpackView

    fun setView(backpackView: BackpackView) {
        this.view = backpackView
    }

    /**
     * Sets the loading backpack bar visible and starts the GetPokemonBackPack use case,
     * initiating the observer to catch the results and returning them to the view.
     */
    fun fillBackpack() {
        view.setLoadingBackpackBarVisibility(View.VISIBLE)
        getPokemonBackpack.execute(GetPokemonBackpackObserver(), GetPokemonBackpack.Params())
    }

    private inner class GetPokemonBackpackObserver : DisposableObserver<List<Pokemon>>() {

        override fun onComplete() {
            Log.i(TAG, "getPokemonBackpack completed")
            view.setLoadingBackpackBarVisibility(View.GONE)
        }

        override fun onError(e: Throwable) {
            Log.e(TAG, "getPokemonBackpack onError: $e")
            view.setLoadingBackpackBarVisibility(View.GONE)
        }

        override fun onNext(pokemons: List<Pokemon>) {
            Log.d(TAG, "getPokemonBackpack response = $pokemons")
            if (pokemons.isEmpty()) {
                view.emptyBackpack()
            } else {
                PokemonBackpack.fillBackpack(pokemons)
                view.filledBackpack()
            }
        }
    }
}