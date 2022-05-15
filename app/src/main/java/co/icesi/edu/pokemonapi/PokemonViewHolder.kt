package co.icesi.edu.pokemonapi

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import co.icesi.edu.pokemonapi.adapter.PokemonAdapter
import co.icesi.edu.pokemonapi.model.Pokemon
import co.icesi.edu.pokemonapi.model.User

class PokemonViewHolder(itemView: View, listener: PokemonAdapter.ClickPokemon) : RecyclerView.ViewHolder(itemView){
    var pokemon: Pokemon? = null
    var user: User? = null
    var pokemonImage: ImageView = itemView.findViewById(R.id.pokemonImage)
    var pokemonName: TextView= itemView.findViewById(R.id.pokemonName)
    var pokemonDate: TextView= itemView.findViewById(R.id.catchDate)

    init{
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}