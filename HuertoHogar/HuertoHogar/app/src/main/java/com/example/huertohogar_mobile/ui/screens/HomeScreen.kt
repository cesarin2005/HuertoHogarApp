package com.example.huertohogar_mobile.ui.screens

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController // Necesario para la Preview
import com.example.huertohogar_mobile.navigation.Screen.* // Importa Home, Profile, Detail, Settings
import com.example.huertohogar_mobile.navigation.Screen
import com.example.huertohogar_mobile.viewmodel.MainViewModel
import com.example.huertohogar_mobile.R
import androidx.compose.ui.tooling.preview.Preview
import com.example.huertohogar_mobile.ui.HuertoHogarTheme // Asegúrate de que el tema esté importado

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    // ✅ INYECCIÓN DEL VIEWMODEL Y NAVCONTROLLER
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
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

            // 🟩 Botón principal: Navega a la pantalla de Detalle con un ID
            Button(
                onClick = {
                    // ✅ ACCIÓN MVVM
                    viewModel.navigateTo(Detail(itemId = "lista_productos"))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Ver productos", color = MaterialTheme.colorScheme.onPrimary)
            }

            // 🟩 Segundo botón: Navega a la pantalla de Perfil
            OutlinedButton(
                onClick = {
                    // ✅ ACCIÓN MVVM
                    viewModel.navigateTo(Profile)
                }
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

                    // Botón dentro de tarjeta: Navega a la pantalla de Configuración
                    Button(onClick = {
                        // ✅ ACCIÓN MVVM
                        viewModel.navigateTo(Settings)
                    }) {
                        Text("Agregar al carrito")
                    }
                }
            }
        }
    }
}

// ----------------------------------------------------------------------
// PREVIEW (Para visualizar el diseño de la pantalla)
// ----------------------------------------------------------------------

@Preview(showBackground = true, name = "Diseño Home Final")
@Composable
fun HomeScreenPreview() {
    // Creamos mocks necesarios para el Preview
    val navController = rememberNavController()

    HuertoHogarTheme {
        // ✅ Llama a la función HomeScreen con los parámetros simulados
        HomeScreen(navController = navController)
    }
}
