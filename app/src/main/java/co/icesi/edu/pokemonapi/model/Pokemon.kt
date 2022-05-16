package co.icesi.edu.pokemonapi.model

import java.io.Serializable

data class Pokemon(
    var id: Int=0,
    var name: String="",
    var sprites: Sprites?=null,
    var types: List<Type> = emptyList(),
    var stats: List<Stat> = emptyList(),
    var date: String=""
):Serializable

