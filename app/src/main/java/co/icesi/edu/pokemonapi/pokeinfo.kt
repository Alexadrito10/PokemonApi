package co.icesi.edu.pokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.icesi.edu.pokemonapi.databinding.ActivityPokeinfoBinding
import co.icesi.edu.pokemonapi.model.Pokemon
import com.google.firebase.firestore.auth.User

class pokeinfo : AppCompatActivity() {
    private val binding:ActivityPokeinfoBinding by lazy {
        ActivityPokeinfoBinding.inflate(layoutInflater)
    }
    private lateinit var user:User
    private lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        user = intent.extras?.get("user") as User
        pokemon = intent.extras?.get("pokemon") as Pokemon

        binding.seeDefenseNum.text = pokemon.


    }
}