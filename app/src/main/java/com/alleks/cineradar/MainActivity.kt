package com.alleks.cineradar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alleks.cineradar.core.di.AppContainer
import com.alleks.cineradar.core.navigation.NavigationWrapper
import com.example.compose.CineRadarTheme

class MainActivity : ComponentActivity() {
    
    private lateinit var appContainer: AppContainer
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Inicializar el contenedor de dependencias
        appContainer = AppContainer(applicationContext)
        
        enableEdgeToEdge()
        setContent {
            CineRadarTheme {
                NavigationWrapper(appContainer = appContainer)
            }
        }
    }
}