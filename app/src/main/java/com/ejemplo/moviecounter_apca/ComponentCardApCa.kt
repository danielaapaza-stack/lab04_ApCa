package com.ejemplo.moviecounter_apca

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ComponentCardApCa() {
    Card(modifier = Modifier.padding(16.dp)) {
        Text(text = "Tarjeta ApCa", modifier = Modifier.padding(16.dp))
    }
}

