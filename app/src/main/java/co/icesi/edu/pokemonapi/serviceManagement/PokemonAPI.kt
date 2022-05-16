package co.icesi.edu.pokemonapi.serviceManagement

import co.icesi.edu.pokemonapi.model.Pokemon
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {
    @GET("api/v2/pokemon/{namePokemon}")
    suspend fun getPokemonByName(@Path("namePokemon")namePokemon: String) : Response<Pokemon>


}