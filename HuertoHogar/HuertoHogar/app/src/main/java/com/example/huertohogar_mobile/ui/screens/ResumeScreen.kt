package com.example.huertohogar_mobile.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.huertohogar_mobile.viewmodel.UsuarioViewModel

/**
 * 📊 Pantalla de Resumen que observa los datos del formulario de registro
 * a través del mismo UsuarioViewModel, demostrando un ViewModel Compartido.
 */
@Composable
fun ResumenScreen(
    // 💡 Inyección de NavController y del ViewModel Compartido
    navController: NavController,
    viewModel: UsuarioViewModel = viewModel()
) {
    // ✅ 1. Observar el estado (UiState) del ViewModel de forma reactiva
    val estado by viewModel.estado.collectAsState()

    // Acceso directo a los datos del estado
    val datos = estado

    Scaffold(
        topBar = {
            // Nota: Aquí podrías añadir un TopAppBar con un botón de retroceso
            // TopAppBar(title = { Text("Resumen de Datos") })
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Text(
                text = "Resumen del Registro",
                style = MaterialTheme.typography.headlineMedium
            )

            // ----------------------------------------------------
            // 2. VISUALIZACIÓN DE DATOS REACTIVOS
            // ----------------------------------------------------

            // Nombre
            Text(text = "Nombre: ${datos.nombre}")

            // Correo
            Text(text = "Correo: ${datos.correo}")

            // Dirección
            Text(text = "Dirección: ${datos.direccion}")

            // Contraseña (Ocultada por seguridad y fines de demostración)
            Text(text = "Contraseña: ${"*".repeat(datos.clave.length)}")

            // Términos Aceptados
            val terminosStatus = if (datos.aceptaTerminos) "Aceptados" else "No aceptados"
            Text(text = "Términos: $terminosStatus")

            // 💡 Aquí se pueden añadir Textos adicionales o un botón de "Volver"
        }
    }
}