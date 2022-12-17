package com.example.aorgia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.aorgia.api.model.AuthApiViewModel
import com.example.aorgia.app.navigation.AppNavigation
import com.example.aorgia.ui.theme.AorgiaTheme
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AorgiaTheme {
                val navController = rememberNavController()
                val authApiViewModel = viewModel<AuthApiViewModel>()
                FirebaseApp.initializeApp(LocalContext.current)
                AppNavigation(navController, authApiViewModel)
            }
        }
    }
}