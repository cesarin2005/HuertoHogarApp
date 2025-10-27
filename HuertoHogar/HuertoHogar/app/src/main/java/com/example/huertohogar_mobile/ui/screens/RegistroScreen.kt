package com.example.huertohogar_mobile.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.huertohogar_mobile.viewmodel.UsuarioViewModel
import com.example.huertohogar_mobile.viewmodel.MainViewModel
import androidx.compose.material3.ExperimentalMaterial3Api

/**
 * 📝 Pantalla de Registro de Usuario (Formulario Reactivo).
 * Implementa la validación en tiempo real y usa StateFlow para manejar el estado.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(
    navController: NavController,
    // 💡 ViewModel principal para la navegación
    mainViewModel: MainViewModel = viewModel(),
    // 💡 ViewModel de la vista para el estado del formulario
    usuarioViewModel: UsuarioViewModel = viewModel()
) {
    // 1. Observar el estado del formulario (UiState)
    val estado by usuarioViewModel.estado.collectAsState()

    // Usamos LazyColumn para que la pantalla sea desplazable si hay muchos campos
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Registro de Usuario") })
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Título
            Text(
                text = "Ingresa tus datos",
                style = MaterialTheme.typography.headlineSmall
            )

            // ----------------------------------------------------
            // 2. CAMPOS DEL FORMULARIO (OutlinedTextField)
            // ----------------------------------------------------

            // Campo NOMBRE
            OutlinedTextField(
                value = estado.nombre,
                onValueChange = usuarioViewModel::onNombreChange,
                label = { Text("Nombre de usuario") },
                isError = estado.errores.nombre != null,
                supportingText = {
                    // Mostrar error si existe
                    estado.errores.nombre?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo CORREO ELECTRÓNICO
            OutlinedTextField(
                value = estado.correo,
                onValueChange = usuarioViewModel::onCorreoChange,
                label = { Text("Correo electrónico") },
                isError = estado.errores.correo != null,
                supportingText = {
                    estado.errores.correo?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo CLAVE (Contraseña)
            OutlinedTextField(
                value = estado.clave,
                onValueChange = usuarioViewModel::onClaveChange,
                label = { Text("Contraseña") },
                // 💡 Uso de PasswordVisualTransformation para ocultar la clave
                visualTransformation = PasswordVisualTransformation(),
                isError = estado.errores.clave != null,
                supportingText = {
                    estado.errores.clave?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo DIRECCIÓN
            OutlinedTextField(
                value = estado.direccion,
                onValueChange = usuarioViewModel::onDireccionChange,
                label = { Text("Dirección") },
                isError = estado.errores.direccion != null,
                supportingText = {
                    estado.errores.direccion?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // ----------------------------------------------------
            // 3. CHECKBOX (Aceptar Términos)
            // ----------------------------------------------------
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = estado.aceptaTerminos,
                    onCheckedChange = usuarioViewModel::onAceptarTerminosChange
                )
                Spacer(Modifier.width(8.dp))
                Text(text = "Acepto los términos y condiciones")
            }

            // ----------------------------------------------------
            // 4. BOTÓN ENVIAR (Submit)
            // ----------------------------------------------------
            Button(
                onClick = {
                    // 💡 Lógica: Al hacer clic, si todo es válido, navegar a "resumen"
                    if (usuarioViewModel.validarFormulario()) {
                        // Navegar al resumen (ejecutar la acción del ViewModel)
                        usuarioViewModel.onRegistroSubmit(mainViewModel)

                        // ❌ Nota: El navController.navigate("resumen") no se usa directamente aquí
                        // ya que la navegación la EMITE el ViewModel.
                    }
                },
                // Deshabilitar si está cargando o si hay errores (opcional)
                enabled = !estado.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = if (estado.isLoading) "Registrando..." else "Registrar")
            }
        }
    }
}