package com.example.a0911_app.data

import com.google.gson.annotations.SerializedName

data class Personaje(
    val id: Int,
    val name: String,
    val status: String,
    @SerializedName("species")
    val specie: String,
    @SerializedName("image")
    val imagen: String
)
