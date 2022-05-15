package co.icesi.edu.pokemonapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import co.icesi.edu.pokemonapi.databinding.ActivityPokepreviewBinding
import co.icesi.edu.pokemonapi.model.Pokemon
import co.icesi.edu.pokemonapi.model.User
import com.bumptech.glide.Glide
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        // TOCARIA CAMBIAR ESTO

        binding.previewDefenseNum.text= pokemon?.defense.toString()
        binding.previewAtkNum.text= pokemon?.attack.toString()
        binding.previewSpeedNum.text= pokemon?.attack.toString()
        binding.previewLifeNum.text= pokemon?.health.toString()
        Glide.with(binding.previewPokeImg.context).load(pokemon?.image.toString()).into(binding.previewPokeImg)
        binding.previewPokeName.text= pokemon?.name.toString()

        binding.previewLiberate.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                Firebase.firestore.collection("users").document(user?.username.toString()).collection("pokemons")
                    .document(pokemon?.id.toString()).delete()
                Firebase.firestore.collection("users").document(user?.username.toString()).collection("pokemons")
                    .orderBy("date", Query.Direction.DESCENDING).get().addOnCompleteListener{ task->
                        for (doc in task.result!!) {
                            doc.reference.delete()
                        }
                    }
                    val intent= Intent(this@pokepreview, dex::class.java).apply{
                    putExtra("user", user)
                    }
                    startActivity(intent)
                    finish()
            }
        }
    }
}