package co.icesi.edu.pokemonapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.icesi.edu.pokemonapi.R
import co.icesi.edu.pokemonapi.databinding.PokemonrowBinding
import co.icesi.edu.pokemonapi.model.Pokemon
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PokemonAdapter(private var pokemones:ArrayList<Pokemon>, var onClickListener: OnClickListener) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    class PokemonViewHolder(var binding: PokemonrowBinding, var onClickListener: OnClickListener):RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onClickListener.onClick(adapterPosition)
        }

    }
    //Override methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(PokemonrowBinding.inflate(LayoutInflater.from(parent.context),parent,false), onClickListener)
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

    interface OnClickListener{
        fun onClick(position: Int)
    }
}