package com.example.huertohogar_mobile.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.huertohogar_mobile.R
import com.example.huertohogar_mobile.ui.HuertoHogarTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass // Importa la clase para tipado
import androidx.compose.material3.windowsizeclass.WindowSizeClass.Companion.calculateFromSize // Importa la función de utilidad

// ✅ Importación del Router (AppScreen) para las Previews
import com.example.huertohogar_mobile.ui.screens.AppScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenCompact() {
    // 💡 La vista está lista para aceptar 'state' y 'onEvent' del ViewModel
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Huerto Hogar") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Bienvenido!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )

            // ⚠️ FUTURO: Este onClick se conectará al ViewModel para navegar
            Button(onClick = { /* acción futura MVVM */ }) {
                Text(text = "Presióname")
            }

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo App",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}


// ----------------------------------------------------------------------
// PREVIEWS (Simulan los diferentes tamaños de ventana llamando al Router AppScreen)
// ----------------------------------------------------------------------

// 💡 Previsualización simple (Home Compacto)
@Preview(showBackground = true, name = "Diseño Compacto Base")
@Composable
fun HomeScreenCompactSimplePreview() {
    HuertoHogarTheme {
        HomeScreenCompact()
    }
}

// VISTA PREVIA COMPACTA (Simula un teléfono)
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(name = "1. Compact", widthDp = 360, heightDp = 800)
@Composable
fun PreviewCompact() {
    val mockSizeClass = calculateFromSize(DpSize(360.dp, 800.dp))

    HuertoHogarTheme {
        AppScreen(windowSizeClass = mockSizeClass)
    }
}

// VISTA PREVIA MEDIANA (Simula una tablet pequeña)
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(name = "2. Mediana", widthDp = 600, heightDp = 1000)
@Composable
fun PreviewMediana() {
    val mockSizeClass = calculateFromSize(DpSize(600.dp, 1000.dp))

    HuertoHogarTheme {
        AppScreen(windowSizeClass = mockSizeClass)
    }
}

// VISTA PREVIA EXPANDIDA (Simula una tablet grande o escritorio)
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(name = "3. Expandida", widthDp = 840, heightDp = 1000)
@Composable
fun PreviewExpandida() {
    val mockSizeClass = calculateFromSize(DpSize(840.dp, 1000.dp))

    HuertoHogarTheme {
        AppScreen(windowSizeClass = mockSizeClass)
    }
}