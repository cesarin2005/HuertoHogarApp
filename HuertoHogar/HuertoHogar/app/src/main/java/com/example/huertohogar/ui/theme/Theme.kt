package com.example.huertohogar.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = VerdePrincipal,
    onPrimary = Color.White,
    secondary = VerdeClaro,
    onSecondary = Color.White,
    background = BlancoSuave,
    onBackground = Color.Black,
    surface = MarronClaro,
    onSurface = Color.Black
)

private val DarkColors = darkColorScheme(
    primary = VerdeClaro,
    onPrimary = Color.Black,
    secondary = VerdePrincipal,
    onSecondary = Color.White,
    background = Color(0xFF1B1B1B),
    onBackground = Color.White,
    surface = Color(0xFF2C2C2C),
    onSurface = Color.White
)

@Composable
fun HuertoHogarTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,   // <-- usa la tipografía correcta
        content = content
    )
}
