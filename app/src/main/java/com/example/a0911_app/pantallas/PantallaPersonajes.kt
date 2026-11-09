package com.example.a0911_app.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.a0911_app.data.Personaje
import com.example.a0911_app.viewmodel.CharacterViewModel

@Composable
fun PantallaPersonajes(viewModel: CharacterViewModel = viewModel()) {
    // variables de inicializacion antes de dibujar
    val personajes = viewModel.personajes
    val isLoading = viewModel.isLoading
    val errorMensaje = viewModel.errorMensaje

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text(
            text = "Personajes de Rick y Morty",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { viewModel.loadCharacters() },
            enabled = !isLoading
        ) {
            Text("Actualizar personajes")
        }

        Spacer(modifier = Modifier.height(15.dp))

        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            errorMensaje != null -> {
                Text(
                    text = errorMensaje,
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = personajes,
                        key = { it.id }
                    ) { personaje ->
                        TarjetaPersonaje(personaje = personaje)
                    }
                }
            }
        }
    }
}

@Composable
fun TarjetaPersonaje(personaje: Personaje) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = personaje.imagen,
                contentDescription = "Imagen de ${personaje.name}",
                modifier = Modifier.size(90.dp),
            contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(15.dp))
            Column() {
                Text(
                    text = personaje.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Especie: ${personaje.specie}"
                )

                Text(
                    text = "Estado: ${personaje.status}"
                )
            }

        }
    }


}


