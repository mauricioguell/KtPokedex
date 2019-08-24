package com.mguell.ktpokedex.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import com.mguell.ktpokedex.domain.model.Pokemon
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    protected lateinit var mCallback: PokemonFragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
        try {
            mCallback = context as PokemonFragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement OnHeadlineSelectedListener")
        }
    }

    private fun initDagger() {
        AndroidSupportInjection.inject(this)
    }

    interface PokemonFragmentListener {
        fun openBackpack()
        fun searchPokemon()
        fun connectionError()
        fun openPokemonDetails(pokemon: Pokemon)
        fun setSearchButtonVisibility(visibility: Int)
    }
}