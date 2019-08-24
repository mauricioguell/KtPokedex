package com.mguell.ktpokedex.presentation

import android.os.Bundle
import com.mguell.ktpokedex.R
import com.mguell.ktpokedex.domain.model.Pokemon
import com.mguell.ktpokedex.presentation.backpack.BackpackFragment
import com.mguell.ktpokedex.presentation.details.DetailsFragment
import com.mguell.ktpokedex.presentation.nointernet.NoInternetFragment
import com.mguell.ktpokedex.presentation.searchpokemon.SearchPokemonFragment
import com.mguell.ktpokedex.utils.Constants.POKEMON_SERIALIZABLE_KEY
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BaseFragment.PokemonFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab.setOnClickListener {
            searchPokemon()
        }
        openBackpack()
    }

    override fun openPokemonDetails(pokemon: Pokemon) {
        val args = Bundle()
        args.putSerializable(POKEMON_SERIALIZABLE_KEY, pokemon)
        val fragment = DetailsFragment.newInstance()
        fragment.arguments = args
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commitAllowingStateLoss()
    }

    override fun openBackpack() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, BackpackFragment.newInstance())
            .commitAllowingStateLoss()
    }

    override fun searchPokemon() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, SearchPokemonFragment.newInstance())
            .commitAllowingStateLoss()
    }

    override fun connectionError() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, NoInternetFragment.newInstance())
            .commitAllowingStateLoss()
    }

    override fun setSearchButtonVisibility(visibility: Int) {
        fab.visibility = visibility
    }
}
