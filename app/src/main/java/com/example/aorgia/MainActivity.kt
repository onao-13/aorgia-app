package com.example.aorgia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.aorgia.api.model.AuthViewModel
import com.example.aorgia.app.navigation.AppNavigation
import com.example.aorgia.ui.theme.AorgiaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AorgiaTheme {
                val navController = rememberNavController()
                val authViewModel = viewModel<AuthViewModel>()
                AppNavigation(navController, authViewModel)
            }
        }
    }
}