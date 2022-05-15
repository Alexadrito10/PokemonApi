package co.icesi.edu.pokemonapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.icesi.edu.pokemonapi.databinding.ActivityPokeinfoBinding
import co.icesi.edu.pokemonapi.model.Pokemon
import co.icesi.edu.pokemonapi.model.User
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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

        binding.seeDefenseNum.text = pokemon?.defense.toString()
        binding.seeAtkNum.text = pokemon?.attack.toString()
        binding.seeSpeedNum.text = pokemon?.speed.toString()
        binding.seeLifeNum.text = pokemon?.health.toString()
        binding.seePokeName.text = pokemon?.name.toString() + " ( "+pokemon.type.toString() + " )"
        Glide.with(binding.seePokeImg.context).load(pokemon?.image.toString()).into(binding.seePokeImg)

        binding.seeCatch.setOnClickListener {
            Firebase.firestore.collection("users").document(user?.username).collection("pokemons").document(pokemon.id).set(pokemon)
            val intent = Intent(this, dex::class.java).apply {
                putExtra("user",user)
            }
            startActivity(intent)

        }






    }
}