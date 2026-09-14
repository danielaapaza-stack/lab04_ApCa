package com.ejemplo.moviecounter_apca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ejemplo.moviecounter_apca.ui.theme.MovieCounter_ApCaTheme

// 1. Clase para mantener el estado individual de cada elemento
class MovieItemApCa(val id: Int, val name: String, initialChecked: Boolean = false) {
    var checked by mutableStateOf(initialChecked)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieCounter_ApCaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EstadosScreenApCa(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// 2. Componente Stateful que maneja la colección de datos
@Composable
fun EstadosScreenApCa(modifier: Modifier = Modifier) {
    val movies = remember {
        mutableStateListOf(
            MovieItemApCa(1, "Interstellar ApCa"),
            MovieItemApCa(2, "Dune ApCa"),
            MovieItemApCa(3, "Inception ApCa")
        )
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Películas por ver: ${movies.size}",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(
                items = movies,
                key = { movie -> movie.id }
            ) { movie ->
                MovieRowApCa(
                    movieName = movie.name,
                    checked = movie.checked,
                    onCheckedChange = { movie.checked = it },
                    onClose = { movies.remove(movie) }
                )
            }
        }
    }
}

// 3. Componente Stateless (Elevación de Estado mediante lambdas)
@Composable
fun MovieRowApCa(
    movieName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = movieName, modifier = Modifier.weight(1f))
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        IconButton(onClick = onClose) {
            Text(text = "X")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEstadosApCa() {
    EstadosScreenApCa()
}