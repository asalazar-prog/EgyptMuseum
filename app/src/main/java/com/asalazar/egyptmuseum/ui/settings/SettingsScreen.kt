package com.asalazar.egyptmuseum.ui.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asalazar.egyptmuseum.domain.settings.model.AgeCategory
import com.asalazar.egyptmuseum.ui.theme.EgyptMuseumTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    onPressedBack: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val configurationState by viewModel.uiState.collectAsStateWithLifecycle()

    val actions = remember(viewModel) {
        ConfigurationUiActions(
            onFontScaleChange = viewModel::updateFontScale,
            onCategorySelected = viewModel::updateAgeCategory,
            onPressedBack = onPressedBack
        )
    }

    SettingsContent(uiState = configurationState, actions = actions)
}

@Composable
private fun SettingsContent(
    uiState: ConfigurationUiState,
    actions: ConfigurationUiActions
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        topBar = { ConfigurationTopBar(actions.onPressedBack) }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FontSizeCard(uiState.fontScale, onValueChange = actions.onFontScaleChange)
            AgeFilterCard(
                selectedCategory = uiState.selectedCategory,
                onCategorySelected = actions.onCategorySelected
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigurationTopBar(onPressedBack: () -> Unit) {
    MediumTopAppBar(
        title = { Title() },
        navigationIcon = {
            IconButton(onClick = onPressedBack) {
                Icon(
                    imageVector = Icons.Sharp.ArrowBack,
                    contentDescription = "Atras"
                )
            }
        }
    )
}

@Composable
private fun Title(modifier: Modifier = Modifier) {
    Text(
        text = "Accesibilidad",
        modifier = modifier
    )
}

data class ConfigurationUiState(
    val fontScale: Float = 1.0f,
    val selectedCategory: AgeCategory = AgeCategory.TEENS,
    val isHighContrastEnabled: Boolean = false
)

data class ConfigurationUiActions(
    val onFontScaleChange: (Float) -> Unit,
    val onCategorySelected: (AgeCategory) -> Unit,
    val onToggleContrast: (Boolean) -> Unit = {},
    val onPressedBack: () -> Unit
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Preview
@Composable
private fun SettingsContentPreview() {
    var fontScale by remember { mutableFloatStateOf(1.0f) }
    var category by remember { mutableStateOf(AgeCategory.KIDS) }

    EgyptMuseumTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SettingsContent(
                uiState = ConfigurationUiState(fontScale, category),
                actions = ConfigurationUiActions(
                    onFontScaleChange = { fontScale = it },
                    onCategorySelected = { category = it },
                    onPressedBack = {}
                )
            )
        }
    }
}
