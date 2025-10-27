package com.example.huertohogar_mobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogar_mobile.ui.model.UsuarioErrores
import com.example.huertohogar_mobile.ui.model.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.huertohogar_mobile.navigation.Screen

// Nota: Asume que MainViewModel, NavigationEvent, y Screen están definidos.

class UsuarioViewModel : ViewModel() {

    private val _estado = MutableStateFlow(UsuarioUiState())
    val estado: StateFlow<UsuarioUiState> = _estado.asStateFlow()

    // ----------------------------------------------------------------------
    // 1. FUNCIONES DE MODIFICACIÓN DE CAMPO (OnXChange)
    // ----------------------------------------------------------------------

    fun onNombreChange(valor: String) {
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }

    fun onCorreoChange(valor: String) {
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    fun onDireccionChange(valor: String) {
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }

    fun onAceptarTerminosChange(valor: Boolean) {
        _estado.update { it.copy(aceptaTerminos = valor) }
    }

    // ----------------------------------------------------------------------
    // 2. FUNCIÓN DE VALIDACIÓN GLOBAL
    // ----------------------------------------------------------------------

    /**
     * 🔐 Validación global del formulario.
     * Retorna true si no hay errores y actualiza el estado interno (errores).
     */
    fun validarFormulario(): Boolean {
        val estadoActual = _estado.value

        // Crear objeto de errores basado en las reglas de validación
        val errores = UsuarioErrores(
            nombre = if (estadoActual.nombre.isBlank()) "Campo obligatorio" else null,
            correo = if (!estadoActual.correo.contains("@")) "Correo inválido" else null,
            clave = if (estadoActual.clave.length < 6) "Debe tener al menos 6 caracteres" else null,
            direccion = if (estadoActual.direccion.isBlank()) "Campo obligatorio" else null
        )

        // Comprobar si existe CUALQUIER error en la lista
        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.correo,
            errores.clave,
            errores.direccion
        ).isNotEmpty() // Retorna true si la lista de errores no está vacía

        // 💡 Actualizar el Estado (UiState) con los nuevos errores
        _estado.update { it.copy(errores = errores) }

        // Retorna true si NO hay errores
        return !hayErrores
    }

    // ----------------------------------------------------------------------
    // 3. FUNCIÓN DE ENVÍO FINAL (Integración con la navegación)
    // ----------------------------------------------------------------------

    fun onRegistroSubmit(mainViewModel: MainViewModel) {
        // 1. Ejecutar la validación global
        if (validarFormulario()) {
            val currentState = _estado.value

            // 2. Comprobar Términos de aceptación (lógica adicional)
            if (!currentState.aceptaTerminos) {
                // Esto podría emitir un Snackbar o error en la UI si fuera necesario
                return
            }

            // 3. Simular la carga y el envío
            _estado.update { it.copy(isLoading = true) }

            viewModelScope.launch {
                // Simulación de una llamada a la API (1 segundo de retardo)
                kotlinx.coroutines.delay(1000)

                // 4. Actualizar el estado y navegar a la pantalla de visualización
                _estado.update { it.copy(
                    isRegistrationSuccessful = true,
                    isLoading = false
                ) }

                // 5. Navegar a la pantalla de visualización de datos (ej. Profile, Settings, o una nueva ruta)
                // Esto demuestra el flujo completo de la actividad práctica.
                mainViewModel.navigateTo(Screen.Profile)
            }
        }
    }
}