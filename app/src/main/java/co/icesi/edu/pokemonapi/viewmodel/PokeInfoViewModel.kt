package co.icesi.edu.pokemonapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.icesi.edu.pokemonapi.model.Pokemon
import co.icesi.edu.pokemonapi.serviceManagement.PokemonService
import co.icesi.edu.pokemonapi.serviceManagement.request

class PokeInfoViewModel:ViewModel()  {
    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon:LiveData<Pokemon> = _pokemon

    fun getPokemon(namePokemon:String){
        request(viewModelScope){
            val response = PokemonService.api.getPokemonByName(namePokemon)

            if(response.isSuccessful) _pokemon.value=response.body()

        }
    }

}