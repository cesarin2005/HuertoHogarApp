package com.example.huertohogar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.huertohogar.ui.screens.HomeScreen
import com.example.huertohogar.ui.HuertoHogarTheme

class MainActivity : ComponentActivity() {
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
