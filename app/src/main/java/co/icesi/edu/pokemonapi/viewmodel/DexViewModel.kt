package co.icesi.edu.pokemonapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.icesi.edu.pokemonapi.model.Pokemon
import co.icesi.edu.pokemonapi.model.User
import co.icesi.edu.pokemonapi.serviceManagement.PokemonService
import co.icesi.edu.pokemonapi.serviceManagement.Session
import co.icesi.edu.pokemonapi.serviceManagement.request
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime

class DexViewModel:ViewModel() {
    private val _listPokemons = MutableLiveData<ArrayList<Pokemon>>()
    val listPokemons: LiveData<ArrayList<Pokemon>> = _listPokemons
    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon:LiveData<Pokemon> = _pokemon

    fun getPokemons(){
        Firebase.firestore.collection("users").document(Session.sessionId).get().addOnSuccessListener {

            val user = it.toObject(User::class.java)
            _listPokemons.value = user?.pokemons as ArrayList<Pokemon>
            Log.e("usuario", user.toString())
        }
    }
    fun getPokemon(namePokemon:String){
        request(viewModelScope){
            val response = PokemonService.api.getPokemonByName(namePokemon)

            if(response.isSuccessful) {
                val myPokemon = response.body()
                myPokemon?.date = LocalDateTime.now().toString()
                _pokemon.value=response.body()


            }
        }
    }
}