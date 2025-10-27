package com.example.huertohogar_mobile.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.* import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController // Necesario para la Preview
import com.example.huertohogar_mobile.navigation.Screen.* // Importa Home, Profile, Settings
import com.example.huertohogar_mobile.viewmodel.MainViewModel
import com.example.huertohogar_mobile.ui.HuertoHogarTheme // Importa tu tema
import androidx.compose.ui.tooling.preview.Preview

// Rutas a mostrar en el Bottom Bar (Usando los objetos Home y Profile)
val bottomItems = listOf(Home, Profile)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    var selectedItem by remember { mutableStateOf(1) } // 1 = Profile (índice inicial)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mi Perfil") })
        },
        bottomBar = {
            // ✅ IMPLEMENTACIÓN DEL BOTTOMBAR
            NavigationBar {
                bottomItems.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            // 💡 ACCIÓN MVVM: Navegar con singleTop para evitar duplicados en la pila
                            viewModel.navigateTo(
                                route = screen,
                                singleTop = true
                            )
                        },
                        label = { Text(screen.route.split("_").first().replaceFirstChar { it.uppercase() }) },
                        icon = {
                            Icon(
                                imageVector = when (screen) {
                                    Home -> Icons.Default.Home
                                    Profile -> Icons.Default.Person
                                    else -> Icons.Default.Home
                                },
                                contentDescription = screen.route
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Pantalla de Perfil",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(text = "Aquí irán los datos del usuario.")
        }
    }
}

// ----------------------------------------------------------------------
// PREVIEW (Para visualizar el diseño de la pantalla)
// ----------------------------------------------------------------------

@Preview(showBackground = true, name = "Diseño Perfil con BottomBar")
@Composable
fun ProfileScreenPreview() {
    // 💡 Crear NavController simulado (Mock) ya que es requerido por ProfileScreen
    val navController = rememberNavController()

    HuertoHogarTheme {
        // ✅ Llamar a la función ProfileScreen con el parámetro simulado
        ProfileScreen(navController = navController)
    }
}