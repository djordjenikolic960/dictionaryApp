package com.example.dictionaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dictionaryapp.features.dictionary.presentation.WordInfo
import com.example.dictionaryapp.features.history.presentation.History
import com.example.dictionaryapp.features.history.presentation.view_model.HistoryViewModel
import com.example.dictionaryapp.features.navigation.presentation.BottomNavigationBar
import com.example.dictionaryapp.ui.theme.DictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            DictionaryAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = { BottomNavigationBar(navController) }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "dictionary",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("dictionary") {
                                WordInfo()
                            }
                            composable("history") {
                                val viewModel: HistoryViewModel = hiltViewModel()
                                History(viewModel.state)
                            }
                        }

                    }
                }
            }
        }
    }
}