package co.icesi.edu.pokemonapi.serviceManagement

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PokemonService {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co")
        .addConverterFactory(MoshiConverterFactory.create()).build()

    val api: PokemonAPI = retrofit.create(PokemonAPI:: class.java)
}