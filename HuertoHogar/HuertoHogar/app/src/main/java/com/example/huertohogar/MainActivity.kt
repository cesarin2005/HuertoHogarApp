package com.example.huertohogar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.huertohogar.ui.HomeScreen
import com.example.huertohogar.ui.theme.HuertoHogarTheme

class MainActivity : ComponentActivity() {
    // ✅ Comentario de prueba para commit en GitHub (César)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔹 Renderiza directamente tu pantalla principal con el tema de la app
        setContent {
            HuertoHogarTheme {
                HomeScreen()
            }
        }
    }
}
