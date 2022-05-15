package co.icesi.edu.pokemonapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.icesi.edu.pokemonapi.PokemonViewHolder
import co.icesi.edu.pokemonapi.R
import co.icesi.edu.pokemonapi.model.Pokemon
import co.icesi.edu.pokemonapi.model.User

class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {
    private var pokemones= ArrayList<Pokemon>()
    private lateinit var user: User
    private lateinit var lis2 : ClickPokemon

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val row= inflater.inflate(R.layout.pokemonrow, parent, false)
        val pokeView = PokemonViewHolder(row, lis2)

        return pokeView
    }


    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    fun setOnClickListener(listener: ClickPokemon){
        lis2 = listener
    }

    interface ClickPokemon{
        fun onItemClick(position: Int)
    }


}