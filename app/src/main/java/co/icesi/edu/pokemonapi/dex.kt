package co.icesi.edu.pokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import co.icesi.edu.pokemonapi.adapter.PokemonAdapter
import co.icesi.edu.pokemonapi.databinding.ActivityDexBinding

class dex : AppCompatActivity() {
    private lateinit var binding: ActivityDexBinding
    private lateinit var username: String
    private val adapter= PokemonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDexBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        username= intent.extras?.getString("username")!!
        val pokemonRecycler= binding.pokeRecycler
        pokemonRecycler.setHasFixedSize(true)
        pokemonRecycler.layoutManager= LinearLayoutManager(this)
        pokemonRecycler.adapter= adapter
        setContentView(binding.root)

        adapter.setOnClickListener(object: PokemonAdapter.ClickPokemon{
            override fun onItemClick(position: Int) {
                TODO("Not yet implemented")
            }
        })

    }
}