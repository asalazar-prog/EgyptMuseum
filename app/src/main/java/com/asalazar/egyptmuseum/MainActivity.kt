package com.asalazar.egyptmuseum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asalazar.egyptmuseum.ui.configuration.ConfigurationScreen
import com.asalazar.egyptmuseum.ui.theme.EgyptMuseumTheme
import com.asalazar.egyptmuseum.ui.welcome.WelcomeScreen
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EgyptMuseumTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome
    ) {
        composable<Screen.Welcome> {
            WelcomeScreen(
                onEnterClick = {},
                onSettingsClick = { navController.navigate(Screen.Configuration) }
            )
        }

        composable<Screen.Configuration> {
            ConfigurationScreen()
        }
    }

}

sealed interface Screen {
    @Serializable
    data object Welcome : Screen

    @Serializable
    data object Configuration : Screen
}




