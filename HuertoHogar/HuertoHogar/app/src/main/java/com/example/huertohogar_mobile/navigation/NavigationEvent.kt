package com.example.huertohogar_mobile.navigation

/**
 * 🔐 Clase Sellada (Sealed Class) que representa los distintos tipos de eventos de navegación.
 * Esta clase es usada por el ViewModel para comunicarse con el NavController.
 */
sealed class NavigationEvent {

    /**
     * Evento para navegar a un destino específico.
     * @param route La ruta de destino (objeto de ruta tipado) a la que navegar.
     * @param popUpToRoute La ruta en la pila de navegación hasta la cual se debe hacer 'pop'.
     * Si es null, no se hace 'pop' a un destino específico.
     * @param inclusive Si 'true', la ruta especificada en [popUpToRoute] también se elimina de la pila.
     * @param singleTop Si 'true', evita múltiples copias del mismo destino en la parte superior
     * de la pila si ya está presente (útil para navegación de barra inferior/lateral).
     */
    data class NavigateTo(
        val route: Screen, // Usa la Sealed Class Screen que creaste
        val popUpToRoute: Screen? = null,
        val inclusive: Boolean = false,
        val singleTop: Boolean = false
    ) : NavigationEvent()

    /**
     * Evento para volver a la pantalla anterior en la pila de navegación.
     */
    data object PopBackstack : NavigationEvent()

    /**
     * Evento para navegar "hacia arriba" en la jerarquía de la aplicación.
     * Generalmente es equivalente a [PopBackstack] a menos que se use un grafo de navegación
     * con una jerarquía padre-hijo definida.
     */
    data object NavigateUp : NavigationEvent()
}