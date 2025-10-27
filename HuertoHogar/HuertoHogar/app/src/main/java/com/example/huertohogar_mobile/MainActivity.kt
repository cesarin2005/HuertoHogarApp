package com.example.huertohogar_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.huertohogar_mobile.ui.HuertoHogarTheme
import com.example.huertohogar_mobile.ui.screens.AppScreen

class MainActivity : ComponentActivity() {
    // 4. ✅ La anotación debe estar aquí
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // ✅ Calcular el tamaño de la ventana (la función ahora está importada)
            val windowSizeClass = calculateWindowSizeClass(this)

            HuertoHogarTheme {
                // Llama al Router
                AppScreen(windowSizeClass = windowSizeClass)
            }
        }
    }
}