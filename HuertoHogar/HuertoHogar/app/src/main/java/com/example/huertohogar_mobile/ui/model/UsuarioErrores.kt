package com.example.huertohogar_mobile.ui.model

/**
 * Modelo que almacena posibles errores individuales del formulario.
 * Un valor 'null' indica que no hay error para ese campo.
 */
data class UsuarioErrores(
    val nombre: String? = null, // Error de validación de Nombre
    val correo: String? = null, // Error de validación de Correo
    val clave: String? = null,  // Error de validación de Clave
    val direccion: String? = null // Error de validación de Dirección
)