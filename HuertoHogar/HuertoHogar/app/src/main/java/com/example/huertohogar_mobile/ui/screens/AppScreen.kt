package com.example.huertohogar_mobile.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.example.huertohogar_mobile.navigation.NavigationEvent
import com.example.huertohogar_mobile.navigation.Screen
import com.example.huertohogar_mobile.viewmodel.MainViewModel

/**
 * 🗺️ Router principal que establece el grafo de navegación y escucha los eventos MVVM.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppScreen(windowSizeClass: WindowSizeClass) {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()
    val navigationEvent by viewModel.navigationEvents.collectAsState(initial = null)

    LaunchedEffect(navigationEvent) {
        navigationEvent?.let { event ->
            when (val navEvent = event) {
                is NavigationEvent.NavigateTo -> {
                    val routeToGo: String = when (val screen = navEvent.route) {
                        is Screen.Detail -> screen.buildRoute()
                        else -> screen.route
                    }

                    val options = navOptions {
                        navEvent.popUpToRoute?.let { screenToPop ->
                            popUpTo(route = screenToPop.route) { inclusive = navEvent.inclusive }
                        }
                        launchSingleTop = navEvent.singleTop
                    }

                    navController.navigate(route = routeToGo, navOptions = options)
                }
                is NavigationEvent.PopBackstack -> navController.popBackStack()
                is NavigationEvent.NavigateUp -> navController.navigateUp()
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController = navController, viewModel = viewModel)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController = navController, viewModel = viewModel)
            }
            composable(Screen.Settings.route) {
                SettingsScreen(navController = navController, viewModel = viewModel)
            }
            composable(
                route = "detail_page/{itemId}", // Usar la plantilla de la ruta directamente
                arguments = listOf(navArgument("itemId") { type = NavType.StringType })
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId") ?: "ID no encontrado"
                Text(text = "Detalle del Producto: $itemId")
            }
        }
    }
}