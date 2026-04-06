package com.asalazar.egyptmuseum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.asalazar.egyptmuseum.domain.discovery.model.CategoryType
import com.asalazar.egyptmuseum.ui.articles.ArticleDetailScreen
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
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val haptic = LocalHapticFeedback.current

    val showTopBar =
        currentDestination?.hierarchy?.any { it.hasRoute(Screen.Welcome::class) } == false

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (showTopBar) {
                GlobalTopBar(
                    currentDestination = currentDestination,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    onBackPressed = {
                        haptic.performHapticFeedback(HapticFeedbackType.Confirm)
                        navController.navigateUp()
                    }
                )
            }
        }
    ) { innerPadding ->
        AppNavigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobalTopBar(
    currentDestination: androidx.navigation.NavDestination?,
    canNavigateBack: Boolean,
    onBackPressed: () -> Unit
) {
    val title = when {
        currentDestination?.hasRoute(Screen.Configuration::class) == true -> stringResource(R.string.title_accesibilidad)
        currentDestination?.hasRoute(Screen.Discovery::class) == true -> stringResource(R.string.title_discovery)
        currentDestination?.hasRoute(Screen.ArticlesScreen::class) == true -> stringResource(R.string.title_discovery)
        else -> ""
    }

    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (canNavigateBack) IconBack(onBackPressed = onBackPressed)
        }
    )
}

@Composable
private fun IconBack(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    IconButton(onClick = onBackPressed, modifier = modifier) {
        Icon(
            imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
            contentDescription = "Atras"
        )
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome,
        modifier = modifier
    ) {
        composable<Screen.Welcome> {
            WelcomeScreen(
                onEnterClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.Confirm)
                    navController.navigate(Screen.Discovery)
                },
                onSettingsClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.Confirm)
                    navController.navigate(Screen.Configuration)
                }
            )
        }

        composable<Screen.Configuration> {
            SettingsScreen()
        }

        composable<Screen.Discovery> {
            DiscoveryScreen(
                onCategorySelected = { type ->
                    haptic.performHapticFeedback(HapticFeedbackType.Confirm)
                    navController.navigate(Screen.ArticlesScreen(type))
                })
        }

        composable<Screen.ArticlesScreen> {
            ArticlesScreen { articleId, categoryType ->
                haptic.performHapticFeedback(HapticFeedbackType.Confirm)
                navController.navigate(Screen.ArticleDetailScreen(categoryType, articleId))
            }
        }

        composable<Screen.ArticleDetailScreen> {
            ArticleDetailScreen()
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

    @Serializable
    data class ArticleDetailScreen(val categoryType: CategoryType, val articleId: Int) : Screen
}
