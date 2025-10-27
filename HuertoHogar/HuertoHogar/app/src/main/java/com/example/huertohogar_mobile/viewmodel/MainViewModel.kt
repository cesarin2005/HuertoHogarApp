package com.example.huertohogar_mobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope // ✅ Se prefiere para el ciclo de vida del ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.example.huertohogar_mobile.navigation.NavigationEvent
import com.example.huertohogar_mobile.navigation.Screen
import com.example.huertohogar_mobile.navigation.Screen.* // Importar rutas para usarlas directamente

/**
 * 🧠 ViewModel principal que gestiona los eventos de navegación de forma segura.
 * Utiliza un Channel para enviar eventos al NavController de la vista.
 */
class MainViewModel : ViewModel() {
    // NOTA: Aquí iría el estado de la UI (HomeState, Products) si estuviera implementado.

    // 1. Canal Privado: Se usa para enviar eventos únicos (como la navegación).
    private val _navigationEvents = Channel<NavigationEvent>()

    // 2. Flujo Público: La vista (NavController) observa este flujo.
    val navigationEvents = _navigationEvents.receiveAsFlow()


    // -------------------------------------------------------------------------
    // FUNCIONES PÚBLICAS PARA LLAMAR DESDE LA VISTA (OnClick, etc.)
    // -------------------------------------------------------------------------

    /**
     * Función para emitir el evento de navegación hacia la ruta deseada,
     * incluyendo parámetros opcionales para el NavController.
     */
    // ✅ CORRECCIÓN: Ahora acepta todos los parámetros definidos en NavigationEvent.NavigateTo
    fun navigateTo(
        route: Screen,
        popUpToRoute: Screen? = null,
        inclusive: Boolean = false,
        singleTop: Boolean = false // <-- Parámetro clave para BottomBar/SideRail
    ) {
        // ✅ MEJORA: Usar viewModelScope para cancelar la coroutine cuando el ViewModel se destruye.
        viewModelScope.launch {
            _navigationEvents.send(
                NavigationEvent.NavigateTo(
                    route = route,
                    popUpToRoute = popUpToRoute,
                    inclusive = inclusive,
                    singleTop = singleTop // Pasa el argumento
                )
            )
        }
    }

    /**
     * Función para volver a la pantalla anterior.
     */
    fun navigateBack() {
        viewModelScope.launch {
            _navigationEvents.send(NavigationEvent.PopBackstack)
        }
    }

    /**
     * Función para navegar hacia arriba (padre).
     */
    fun navigateUp() {
        viewModelScope.launch {
            _navigationEvents.send(NavigationEvent.NavigateUp)
        }
    }
}