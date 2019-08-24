package com.mguell.ktpokedex.presentation.searchpokemon

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.mguell.ktpokedex.R
import com.mguell.ktpokedex.domain.model.Pokemon
import com.mguell.ktpokedex.presentation.BaseFragment
import com.mguell.ktpokedex.utils.Constants.COLON_FORMAT_FLOAT
import com.mguell.ktpokedex.utils.Constants.FLOAT_TEN
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.searched_pokemon_fragment.*
import javax.inject.Inject

class SearchPokemonFragment : BaseFragment(), SearchPokemonView {

    @Inject
    lateinit var searchPokemonPresenter: SearchPokemonPresenter

    @Inject
    lateinit var ctx: Context

    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.searched_pokemon_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnPokeball.setOnClickListener {
            throwPokeball()
        }
        btnEscape.setOnClickListener {
            escapeFromPokemon()
        }
        tvReturnToBackpack.setOnTouchListener { _: View, event: MotionEvent ->
            returnToBackpack(event)
        }
        searchPokemonPresenter.initSearchPokemonPresenter(this)
        mCallback.setSearchButtonVisibility(View.GONE)
    }

    override fun setLoadingPokemonBarVisibility(visibility: Int) {
        pbLoadingPokemon?.visibility = visibility
    }

    override fun setFreePokemon(pokemon: Pokemon) {
        tvWildPokemonAppeared?.text = String.format(
            "%s %s %s",
            getString(R.string.txt_wild),
            pokemon.name,
            getString(R.string.txt_appeared)
        )
        tvHeight?.text = String.format(
            COLON_FORMAT_FLOAT,
            getString(R.string.txt_height),
            pokemon.height / FLOAT_TEN,
            getString(R.string.txt_m)
        )
        tvWeight?.text = String.format(
            COLON_FORMAT_FLOAT,
            getString(R.string.txt_weight),
            pokemon.weight / FLOAT_TEN,
            getString(R.string.txt_kg)
        )
        if (ivPokemon != null) {
            Picasso.get()
                .load(pokemon.posterPath)
                .into(ivPokemon)
        }
        btnPokeball?.visibility = View.VISIBLE
        btnEscape?.visibility = View.VISIBLE
    }

    override fun setCaughtPokemon(pokemon: Pokemon) {
        tvWildPokemonAppeared?.text = String.format(
            "%s %s %s",
            getString(R.string.txt_already_caught),
            pokemon.name,
            getString(R.string.txt_appeared)
        )
        tvHeight?.text = String.format(
            COLON_FORMAT_FLOAT,
            getString(R.string.txt_height),
            pokemon.height / FLOAT_TEN,
            getString(R.string.txt_m)
        )
        tvWeight?.text = String.format(
            COLON_FORMAT_FLOAT,
            getString(R.string.txt_weight),
            pokemon.weight / FLOAT_TEN,
            getString(R.string.txt_kg)
        )
        if (ivPokemon != null) {
            Picasso.get()
                .load(pokemon.posterPath)
                .into(ivPokemon)
        }
        btnPokeball?.visibility = View.GONE
        btnEscape?.visibility = View.VISIBLE
    }

    override fun savePokemonSuccess() {
        Toast.makeText(ctx, getString(R.string.txt_catched), Toast.LENGTH_LONG).show()
        mCallback.openBackpack()
    }

    override fun noPokemonFound() {
        tvWildPokemonAppeared?.text = getString(R.string.msg_no_pokemon_in_area)
        tvReturnToBackpack?.visibility = View.VISIBLE
    }

    override fun connectionError() {
        mCallback.connectionError()
    }

    /**
     * Action of touching the return to backpack TextView, if the event is the start of the touch event,
     * changes the color of the TextView (in order to give some feedback to the user), if the
     * event is the finish, returns to the backpack view via the MainActivity.
     *
     * @param motionEvent press or stop pressing the TextView event.
     * @return true if the method is correctly finished, false otherwise.
     */
    private fun returnToBackpack(motionEvent: MotionEvent): Boolean {
        if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            tvReturnToBackpack?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.accent_dark
                )
            )
        } else if (motionEvent.action == MotionEvent.ACTION_UP) {
            mCallback.openBackpack()
        }
        return true
    }

    private fun throwPokeball() {
        searchPokemonPresenter.throwPokeball()
    }

    private fun escapeFromPokemon() {
        Toast.makeText(ctx, getString(R.string.txt_you_escaped), Toast.LENGTH_LONG).show()
        mCallback.openBackpack()
    }

    companion object {

        fun newInstance(): SearchPokemonFragment {
            return SearchPokemonFragment()
        }
    }
}