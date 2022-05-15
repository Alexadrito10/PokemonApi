package co.icesi.edu.pokemonapi.model

import java.io.Serializable

data class Pokemon(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val type: Type,
    val image: String,
    val stats: List<Stat>,
    val date: Long=0
)

