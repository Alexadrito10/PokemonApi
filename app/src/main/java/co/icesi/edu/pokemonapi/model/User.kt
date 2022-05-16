package co.icesi.edu.pokemonapi.model

import java.io.Serializable

data class User(
    val id:String="",
    val username:String="",
    var pokemons:List<Pokemon> = emptyList()
):Serializable