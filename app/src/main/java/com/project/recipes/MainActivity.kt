package com.project.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.project.recipes.screens.InitialScreen
import com.project.recipes.screens.SignupScreen
import com.project.recipes.ui.theme.RecipesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesTheme {
                //InitialScreen()
                SignupScreen()
            }
        }
    }
}

