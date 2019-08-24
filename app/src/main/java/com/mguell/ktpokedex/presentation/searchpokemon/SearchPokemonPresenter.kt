package com.mguell.ktpokedex.presentation.searchpokemon

import android.util.Log
import android.view.View
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.mguell.ktpokedex.domain.interactor.GetPokemonById
import com.mguell.ktpokedex.domain.interactor.SavePokemon
import com.mguell.ktpokedex.domain.model.Pokemon
import com.mguell.ktpokedex.domain.model.PokemonBackpack
import com.mguell.ktpokedex.utils.CurrentTimeUtils
import io.reactivex.observers.DisposableObserver

class SearchPokemonPresenter(
    private val getPokemonById: GetPokemonById,
    private val savePokemon: SavePokemon
) {

    companion object {
        private val TAG = this::class.java.simpleName

        private const val MIN_ID = 1
        private const val MAX_ID = 1000
    }

    private lateinit var view: SearchPokemonView
    private lateinit var currentPokemon: Pokemon

    fun initSearchPokemonPresenter(view: SearchPokemonView) {
        this.view = view
        searchPokemon()
    }

    /**
     * Catches the current Pokemon, saving its catched time and starting the SavePokemon use case,
     * initiating the observer to catch the results and returning them to the view.
     */
    fun throwPokeball() {
        currentPokemon.catchedTime = CurrentTimeUtils.getCurrentTime()
        savePokemon.execute(SavePokemonObserver(), currentPokemon)
    }

    /**
     * Sets the loading Pokemon bar visible and starts the GetPokemonById use case with a
     * random number between 1 and 1000 as id, initiating the observer to catch the results
     * and returning them to the view.
     */
    private fun searchPokemon() {
        view.setLoadingPokemonBarVisibility(View.VISIBLE)
        getPokemonById.execute(GetPokemonByIdObserver(), (MIN_ID..MAX_ID).random())
    }

    private inner class GetPokemonByIdObserver : DisposableObserver<Pokemon>() {

        override fun onComplete() {
            Log.i(TAG, "getPokemonById completed")
            view.setLoadingPokemonBarVisibility(View.GONE)
        }

        override fun onError(e: Throwable) {
            Log.e(TAG, "getPokemonById onError: $e")
            if (e is HttpException) {
                view.noPokemonFound()
            } else {
                view.connectionError()
            }
            view.setLoadingPokemonBarVisibility(View.GONE)
        }

        override fun onNext(pokemon: Pokemon) {
            Log.d(TAG, "getPokemonById response = $pokemon")
            currentPokemon = pokemon
            if (PokemonBackpack.getBackpack().contains(pokemon)) {
                view.setCaughtPokemon(pokemon)
            } else {
                view.setFreePokemon(pokemon)
            }
        }
    }

    private inner class SavePokemonObserver : DisposableObserver<Void>() {

        override fun onComplete() {
            Log.i(TAG, "savePokemon completed")
            view.savePokemonSuccess()
        }

        override fun onError(e: Throwable) {
            Log.e(TAG, "savePokemon onError: $e")
        }

        override fun onNext(void: Void) {
            Log.d(TAG, "savePokemon onNext")
        }
    }
}