package co.icesi.edu.pokemonapi.model

import java.io.Serializable

data class Pokemon(
    val id: String ="",
    val name: String ="",
    val image:String="",
    val username: String="",
    val health:String="",
    val attack: String="",
    val defense: String="",
    val speed: String="",
    val type: String="",
    val date: Long=0
):Serializable

