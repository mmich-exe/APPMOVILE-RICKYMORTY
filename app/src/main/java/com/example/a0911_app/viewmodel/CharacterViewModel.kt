package com.example.a0911_app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a0911_app.data.Personaje
import com.example.a0911_app.data.RetrofitClient
import kotlinx.coroutines.launch

class CharacterViewModel: ViewModel() {

    //Obtener lista de personajes
    var personajes by mutableStateOf<List<Personaje>>(emptyList())
        private set
    //estado de la carga
    var isLoading by mutableStateOf(false)
        private set

    //mensaje de error
    var errorMensaje by mutableStateOf<String?>(null)
        private set

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch {
            isLoading=true
            errorMensaje=null
            try {
                //consultamos a la api
                val response= RetrofitClient.api.getCharacter()
                //guardamos lista recibida
                personajes=response.results
            } catch (e: Exception){
                errorMensaje="no es posible cargar personajes"
            }finally {
                isLoading=false
            }
        }
    }
}