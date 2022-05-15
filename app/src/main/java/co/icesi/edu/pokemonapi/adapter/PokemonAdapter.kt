package co.icesi.edu.pokemonapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.icesi.edu.pokemonapi.PokemonViewHolder
import co.icesi.edu.pokemonapi.R
import co.icesi.edu.pokemonapi.model.Pokemon
import co.icesi.edu.pokemonapi.model.User
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {
    private var pokemones= ArrayList<Pokemon>()
    private lateinit var user: User
    private lateinit var lis2 : ClickPokemon

    //Override methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val row= inflater.inflate(R.layout.pokemonrow, parent, false)
        val pokeView = PokemonViewHolder(row, lis2)

        return pokeView
    }

    override fun getItemCount(): Int {
        return pokemones.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val poke = pokemones[position]
        holder.pokemon= poke
        Picasso.get().load(poke.image).into(holder.pokemonImage)
        holder.pokemonName.text= poke.name
        holder.pokemonDate.text= SimpleDateFormat("MMM dd, yy 'at' HH:mm")
            .format(Date(poke.date))
    }

    interface ClickPokemon{
        fun onItemClick(position: Int)
    }

    //Implemented methods to make it functional
    fun addPokemon(poke: Pokemon){
        pokemones.add(poke)
    }

    fun deletePokemon(poke: Pokemon){
        pokemones.remove(poke)
        notifyDataSetChanged()
    }

    fun clear(){
        pokemones.clear()
    }

    fun getPokemon(position: Int): Pokemon{
        return pokemones.get(position)
    }

    fun setOnClickListener(listener: ClickPokemon){
        lis2 = listener
    }


}