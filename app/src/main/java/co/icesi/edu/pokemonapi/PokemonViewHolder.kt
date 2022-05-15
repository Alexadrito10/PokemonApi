package co.icesi.edu.pokemonapi

import android.view.View
import android.widget.ImageView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import co.icesi.edu.pokemonapi.adapter.PokemonAdapter

class PokemonViewHolder(itemView: View, listener: PokemonAdapter.ClickPokemon) : RecyclerView.ViewHolder(itemView){
    var pokemonImage: ImageView = itemView.findViewById(R.id.pokemonImage)
    var pokemonName: ImageView= itemView.findViewById(R.id.pokemonName)
    var pokemonDate: ImageView= itemView.findViewById(R.id.catchDate)

    init{
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}