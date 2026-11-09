package com.example.a0911_app.data

import retrofit2.http.GET

interface RickyMortyAPI {
    //Realiza la solicitud de datos por GET


    @GET("character")
    suspend fun getCharacter(): ListaPersonajes



}