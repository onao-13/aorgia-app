package com.example.aorgia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aorgia.api.model.AuthApiViewModel
import com.example.aorgia.app.navigation.AppNavigation
import com.example.aorgia.app.navigation.Screen
import com.example.aorgia.data.User
import com.example.aorgia.database.model.ProfileDbViewModel
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
                val profileDbViewModel = viewModel<ProfileDbViewModel>()

                AppNavigation(
                    navController,
                    authApiViewModel,
                    profileDbViewModel
                )
            }
        }
    }
}