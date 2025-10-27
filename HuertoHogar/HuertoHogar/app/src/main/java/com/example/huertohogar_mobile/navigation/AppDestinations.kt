package com.example.huertohogar_mobile.navigation

/**
 * Clase Sellada (Sealed Class) para definir Rutas tipo-safe en la navegación.
 * Cada 'object' o 'data class' que hereda de Screen es una ruta de la app.
 */
sealed class Screen(val route: String) {

    // Rutas simples (sin argumentos): usamos data object

    /**
     * Representa la ruta a la pantalla de Inicio.
     */
    data object Home : Screen(route = "home_page")

    /**
     * Representa la ruta a la pantalla de Configuración.
     */
    data object Settings : Screen(route = "settings_page")

    /**
     * Representa la ruta a la pantalla de Perfil (usado como "Sobre Nosotros").
     */
    data object Profile : Screen(route = "profile_page")

    // Ejemplos de Rutas con Argumentos

    /**
     * Representa la ruta a la pantalla de Detalle del Producto.
     * Requiere un 'itemId' como argumento en la URL.
     */
    data class Detail(val itemId: String) : Screen(route = "detail_page/{itemId}") {

        /**
         * Función para construir la ruta final con el argumento real.
         * @return La URL final para la navegación (ej. "detail_page/A123").
         */
        fun buildRoute(): String {
            // Reemplaza el placeholder con el valor real
            val routeWithArgument = route.replace("{itemId}", itemId)
            return routeWithArgument
        }
    }

}