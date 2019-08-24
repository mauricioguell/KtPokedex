package com.mguell.ktpokedex.presentation.backpack

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mguell.ktpokedex.R
import com.mguell.ktpokedex.domain.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.backpack_pokemon_card.view.*

class BackpackAdapter(private val callback: PokemonCardCallback) :
    RecyclerView.Adapter<BackpackAdapter.BackpackViewHolder>() {

    companion object {
        private val TAG = this::class.java.simpleName
    }

    private var backpack: MutableList<Pokemon> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackpackViewHolder {
        return BackpackViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.backpack_pokemon_card,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = backpack.size

    override fun onBindViewHolder(holder: BackpackViewHolder, position: Int) {
        val pokemon = backpack[position]
        holder.name.text = pokemon.name
        if (pokemon.posterPath.isEmpty()) {
            Log.d(TAG, String.format("%s: %s", "Image not found for", pokemon.name))
            holder.poster.setImageDrawable(holder.noImageIc)
        } else {
            Picasso.get()
                .load(backpack[position].posterPath)
                .into(holder.poster)
        }
        holder.cardView.setOnClickListener {
            callback.selectedPokemon(pokemon)
        }
    }

    fun setBackpack(backpack: List<Pokemon>) {
        this.backpack.clear()
        this.backpack.addAll(backpack)
        notifyDataSetChanged()
    }

    inner class BackpackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: AppCompatTextView = itemView.card_pokemon_name
        val poster: AppCompatImageView = itemView.card_pokemon_poster
        val noImageIc: Drawable? =
            ContextCompat.getDrawable(itemView.context, R.drawable.ic_no_image)
        val cardView: CardView = itemView.card_pokemon
    }
}