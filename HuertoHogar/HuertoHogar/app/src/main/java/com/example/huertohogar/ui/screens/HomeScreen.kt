package com.example.huertohogar.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.huertohogar.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        // Usa el color de fondo definido en tu tema
        containerColor = MaterialTheme.colorScheme.background,

        // Barra superior con el color primario (#006337)
        topBar = {
            TopAppBar(
                title = { Text("Huerto Hogar") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        // Contenido principal de la pantalla
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 🟩 Texto de bienvenida
            Text(
                text = "¡Del huerto a tu hogar!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            // 🟩 Imagen del logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Huerto Hogar",
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(140.dp),
                contentScale = ContentScale.Fit
            )

            // 🟩 Botón principal
            Button(
                onClick = { /* acción futura */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Ver productos", color = MaterialTheme.colorScheme.onPrimary)
            }

            // 🟩 Segundo botón
            OutlinedButton(
                onClick = { /* acción futura */ }
            ) {
                Text("Sobre nosotros", color = MaterialTheme.colorScheme.primary)
            }

            // 🟩 Tarjeta ejemplo
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Frutillas Orgánicas", fontWeight = FontWeight.Bold)
                    Text("Frescas, sin pesticidas y de cultivo local.")
                    Button(onClick = { /* acción futura */ }) {
                        Text("Agregar al carrito")
                    }
                }
            }
        }
    }
}

