package br.com.project.newmoodplus2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.project.newmoodplus.ui.components.FormScreen
import br.com.project.newmoodplus.ui.components.HomeScreen
import br.com.project.newmoodplus.ui.components.IntroScreen
import br.com.project.newmoodplus.ui.components.LoginScreen
import br.com.project.newmoodplus.ui.components.MoodValidScreen
import br.com.project.newmoodplus2.ui.theme.NewMoodPlusTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewMoodPlusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "LoginScreen") {
                        composable(route = "IntroScreen") {
                            IntroScreen(
                                navController = navController
                            )
                        }
                        composable(route = "LoginScreen") {
                            LoginScreen(
                                navController = navController
                            )
                        }
                        composable(route = "HomeScreen") {
                            HomeScreen(
                                navController = navController
                            )
                        }
                        composable(route = "FormScreen") {
                            FormScreen(
                                navController = navController
                            )
                        }
                        composable(route = "MoodValidScreen") {
                            MoodValidScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}


