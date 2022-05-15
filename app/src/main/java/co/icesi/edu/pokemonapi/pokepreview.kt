package co.icesi.edu.pokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.icesi.edu.pokemonapi.databinding.ActivityPokepreviewBinding
import co.icesi.edu.pokemonapi.model.Pokemon
import co.icesi.edu.pokemonapi.model.User
import com.bumptech.glide.Glide

class pokepreview : AppCompatActivity() {
    private lateinit var binding: ActivityPokepreviewBinding
    private lateinit var user: User
    private lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPokepreviewBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        user= intent.extras?.get("user") as User
        pokemon = (intent.extras?.get("pokemon") as Pokemon?)!!

        binding.previewDefenseNum.text= pokemon?.defense.toString()
        binding.previewAtkNum.text= pokemon?.attack.toString()
        binding.previewSpeedNum.text= pokemon?.attack.toString()
        binding.previewLifeNum.text= pokemon?.health.toString()
        Glide.with(binding.previewPokeImg).load(pokemon?.image.toString()).into(binding.previewPokeImg)


    }
}