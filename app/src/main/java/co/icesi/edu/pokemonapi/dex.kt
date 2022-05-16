package co.icesi.edu.pokemonapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.icesi.edu.pokemonapi.adapter.PokemonAdapter
import co.icesi.edu.pokemonapi.databinding.ActivityDexBinding
import co.icesi.edu.pokemonapi.model.User
import co.icesi.edu.pokemonapi.serviceManagement.Constant
import co.icesi.edu.pokemonapi.serviceManagement.HTTPSWebUtilDomi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.gson.Gson
import org.json.JSONObject

class dex : AppCompatActivity() {
    private lateinit var binding: ActivityDexBinding
    private lateinit var user: User
    private lateinit var username: String
    private val adapter= PokemonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDexBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        username = intent.extras?.getString("username")!!
        val pokemonRecycler = binding.pokeRecycler
        pokemonRecycler.setHasFixedSize(true)
        pokemonRecycler.layoutManager = LinearLayoutManager(this)
        pokemonRecycler.adapter = adapter
        setContentView(binding.root)
        user = intent.extras?.get("user") as User
        adapter.user = user

        adapter.setOnClickListener(object: PokemonAdapter.ClickPokemon{
            override fun onItemClick(position: Int) {
                val intent= Intent(this@dex, pokepreview::class.java).apply{
                    putExtra("pokemon", adapter.getPokemon(position))
                }
                startActivity(intent)
            }
        })

        binding.catchPokemon.setOnClickListener {
          var nameP= binding.catchPokemon.text.toString()
          if(nameP!=""){
              nameP= nameP.trim()

              lifecycleScope.launch(Dispatchers.IO){
                  try{
                      nameP= nameP.trim()
                      val response = HTTPSWebUtilDomi().GETRequest("${Constant.POKE_API_URL}/pokemon${nameP}")
                      val connectionJson: JSONObject= Gson().fromJson(response, JSONObject::class.java)

                      nameP= nameP.replaceFirstChar { it.uppercaseChar() }
                      val stats= connectionJson["stats"].toString()
                      val types= connectionJson["types"].toString()
                      val images= connectionJson["sprites"].toString()

                      var newPoke= create()

                  }catch(e: Exception){

                  }
              }

          }
        }

    }

    override fun onResume() {
        super.onResume()


    }

    fun pokemonInRecycler(){
        adapter.clear()
        Firebase.firestore.collection("pokemon").whereEqualTo("username", username)
    }

    fun create(){

    }
}