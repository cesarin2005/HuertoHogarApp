package com.example.huertohogar_mobile.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.huertohogar_mobile.ui.screens.RegistroScreen
import com.example.huertohogar_mobile.ui.screens.ResumenScreen
import com.example.huertohogar_mobile.viewmodel.UsuarioViewModel

/**
 * 🧭 Contenedor principal de navegación (NavHost) para el flujo de Registro/Resumen.
 * El UsuarioViewModel se crea aquí para ser compartido por todas las pantallas.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // ✅ CREACIÓN DEL VIEWMODEL COMPARTIDO: Se crea una sola vez en el nivel más alto del NavHost.
    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "registro" // La primera pantalla del flujo
    ) {
        // --- RUTA: REGISTRO ---
        composable(route = "registro") {
            // Se pasa el NavController (para la navegación) y el UsuarioViewModel (para los datos)
            RegistroScreen(
                navController = navController,
                usuarioViewModel = usuarioViewModel
            )
        }

        // --- RUTA: RESUMEN ---
        composable(route = "resumen") {
            // Se pasa solo el UsuarioViewModel, demostrando que los datos están disponibles
            // sin necesidad de argumentos de ruta.
            ResumenScreen(
                navController = navController,
                viewModel = usuarioViewModel
            )
        }
    }
}