package com.mguell.ktpokedex.presentation.backpack

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.GridLayoutManager
import com.mguell.ktpokedex.R
import com.mguell.ktpokedex.domain.model.Pokemon
import com.mguell.ktpokedex.domain.model.PokemonBackpack
import com.mguell.ktpokedex.presentation.BaseFragment
import com.mguell.ktpokedex.utils.RecyclerViewMargin
import kotlinx.android.synthetic.main.backpack_fragment.*
import javax.inject.Inject

class BackpackFragment : BaseFragment(), BackpackView, PokemonCardCallback {

    @Inject
    lateinit var backpackPresenter: BackpackPresenter

    @Inject
    lateinit var ctx: Context

    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.backpack_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val gridLayoutInflater = GridLayoutManager(ctx, 4)
        rvBackpack.layoutManager = gridLayoutInflater
        rvBackpack.adapter = BackpackAdapter(this)
        val decoration = RecyclerViewMargin(
            resources.getDimension(R.dimen.distance_between_recycler_view_items).toInt(),
            resources.getInteger(R.integer.pokemon_list_column_number)
        )
        rvBackpack.addItemDecoration(decoration)
        backpackPresenter.setView(this)
        backpackPresenter.fillBackpack()
        mCallback.setSearchButtonVisibility(View.VISIBLE)
    }

    override fun filledBackpack() {
        tvEmptyBackpack?.visibility = View.GONE
        rvBackpack?.visibility = View.VISIBLE
        (rvBackpack?.adapter as BackpackAdapter).setBackpack(PokemonBackpack.getBackpack())
    }

    override fun setLoadingBackpackBarVisibility(visibility: Int) {
        pbLoadingBackpack.visibility = visibility
    }

    override fun emptyBackpack() {
        tvEmptyBackpack.visibility = View.VISIBLE
        tvEmptyBackpack?.text = getString(R.string.msg_empty_backpack)
    }

    override fun selectedPokemon(pokemon: Pokemon) {
        mCallback.openPokemonDetails(pokemon)
    }

    companion object {

        fun newInstance(): BackpackFragment {
            return BackpackFragment()
        }
    }
}