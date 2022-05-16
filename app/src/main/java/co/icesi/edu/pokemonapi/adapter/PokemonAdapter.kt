package co.icesi.edu.pokemonapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.icesi.edu.pokemonapi.R
import co.icesi.edu.pokemonapi.databinding.PokemonrowBinding
import co.icesi.edu.pokemonapi.model.Pokemon
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PokemonAdapter(private var pokemones:ArrayList<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    //

    private lateinit var lis2 : ClickPokemon
    class PokemonViewHolder(var binding: PokemonrowBinding):RecyclerView.ViewHolder(binding.root)
    //Override methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {


        return PokemonViewHolder(PokemonrowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return pokemones.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val poke = pokemones[position]
        Glide.with(holder.binding.pokemonImage.context).load(poke.sprites?.front_default).into(holder.binding.pokemonImage)
        holder.binding.pokemonName.text= poke.name
        holder.binding.catchDate.text= poke.date
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