package com.asalazar.egyptmuseum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asalazar.egyptmuseum.domain.discovery.model.CategoryType
import com.asalazar.egyptmuseum.ui.articles.ArticlesScreen
import com.asalazar.egyptmuseum.ui.discovery.DiscoveryScreen
import com.asalazar.egyptmuseum.ui.settings.SettingsScreen
import com.asalazar.egyptmuseum.ui.settings.SettingsViewModel
import com.asalazar.egyptmuseum.ui.theme.EgyptMuseumTheme
import com.asalazar.egyptmuseum.ui.welcome.WelcomeScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: SettingsViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            EgyptMuseumTheme(fontScale = uiState.fontScale) {
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
                onEnterClick = { navController.navigate(Screen.Discovery) },
                onSettingsClick = { navController.navigate(Screen.Configuration) }
            )
        }

        composable<Screen.Configuration> {
            SettingsScreen(onPressedBack = navController::navigateUp)
        }

        composable<Screen.Discovery> {
            DiscoveryScreen(
                onBackPressed = navController::navigateUp,
                onCategorySelected = { type ->
                    navController.navigate(Screen.ArticlesScreen(type))
                })
        }

        composable<Screen.ArticlesScreen> {
            ArticlesScreen()
        }
    }
}

sealed interface Screen {
    @Serializable
    data object Welcome : Screen

    @Serializable
    data object Configuration : Screen

    @Serializable
    data object Discovery : Screen

    @Serializable
    data class ArticlesScreen(val categoryType: CategoryType) : Screen
}




