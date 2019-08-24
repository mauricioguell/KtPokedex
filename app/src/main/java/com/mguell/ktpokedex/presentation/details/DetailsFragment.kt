package com.mguell.ktpokedex.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.mguell.ktpokedex.R
import com.mguell.ktpokedex.domain.model.Pokemon
import com.mguell.ktpokedex.presentation.BaseFragment
import com.mguell.ktpokedex.utils.Constants.COLON_FORMAT
import com.mguell.ktpokedex.utils.Constants.COLON_FORMAT_FLOAT
import com.mguell.ktpokedex.utils.Constants.FLOAT_TEN
import com.mguell.ktpokedex.utils.Constants.POKEMON_SERIALIZABLE_KEY
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_fragment.*

class DetailsFragment : BaseFragment() {

    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pokemon: Pokemon = arguments!!.getSerializable(POKEMON_SERIALIZABLE_KEY) as Pokemon
        tvDetailsPokemonName?.text = pokemon.name
        Picasso.get()
            .load(pokemon.posterPath)
            .into(ivDetailsPokemonPoster)
        tvDetailsPokemonCatchedTime?.text = String.format(
            COLON_FORMAT,
            getString(R.string.txt_catched_time),
            pokemon.catchedTime
        )
        tvDetailsPokemonWeight?.text = String.format(
            COLON_FORMAT_FLOAT,
            getString(R.string.txt_weight),
            pokemon.weight / FLOAT_TEN, getString(R.string.txt_kg)
        )
        tvDetailsPokemonHeight?.text = String.format(
            COLON_FORMAT_FLOAT,
            getString(R.string.txt_height),
            pokemon.height / FLOAT_TEN, getString(R.string.txt_m)
        )
        tvDetailsPokemonBaseExperience?.text = String.format(
            COLON_FORMAT_FLOAT,
            getString(R.string.txt_base_experience),
            pokemon.baseExperience.toFloat(), getString(R.string.txt_xp)
        )
        tvDetailsPokemonTypes?.text = String.format(
            COLON_FORMAT,
            getString(R.string.txt_types),
            pokemon.types.toString().replace('[', ' ').replace(']', ' ')
        )
        tvReturnToBackpack?.setOnTouchListener { _: View, event: MotionEvent ->
            returnToBackpack(event)
        }
        mCallback.setSearchButtonVisibility(View.VISIBLE)
    }

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

    companion object {

        fun newInstance(): DetailsFragment {
            return DetailsFragment()
        }
    }
}