package com.asalazar.egyptmuseum.ui.configuration

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.RestartAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asalazar.egyptmuseum.ui.theme.EgyptMuseumTheme
import com.asalazar.egyptmuseum.ui.theme.component.PrimaryButton

@Composable
fun ConfigurationScreen() {

    val viewModel: ConfigurationViewModel =
        remember { ConfigurationViewModel() } /*TODO: Implement factory*/

    val fontScale by viewModel.fontScale.collectAsStateWithLifecycle()
    val ageCategory by viewModel.ageCategory.collectAsStateWithLifecycle()

    val uiState = ConfigurationUiState(
        fontScale = fontScale,
        selectedCategory = ageCategory
    )

    val actions = remember(viewModel) {
        ConfigurationUiActions(
            onFontScaleChange = viewModel::updateFontScale,
            onCategorySelected = viewModel::updateAgeCategory
        )
    }

    ConfigurationContent(uiState = uiState, actions = actions)
}

@Composable
private fun ConfigurationContent(
    uiState: ConfigurationUiState,
    actions: ConfigurationUiActions
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        topBar = { ConfigurationTopBar() },
        bottomBar = {
            BottomBar(modifier = Modifier.padding(16.dp))
        }
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

@Composable
private fun BottomBar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryButton(
            onClick = {},
            modifier = Modifier.widthIn(min = 250.dp)
        ) { Text("ACEPTAR") }

        TextButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                Icons.Sharp.RestartAlt,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text("RESTABLECER", color = MaterialTheme.colorScheme.tertiary)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigurationTopBar() {
    MediumTopAppBar(
        title = { Title() },
        navigationIcon = {
            Icon(
                imageVector = Icons.Sharp.ArrowBack,
                contentDescription = "Atras"
            )
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
    val onToggleContrast: (Boolean) -> Unit = {}
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Preview
@Composable
private fun ConfigurationContentPreview() {
    var fontScale by remember { mutableFloatStateOf(1.0f) }
    var category by remember { mutableStateOf(AgeCategory.KIDS) }

    EgyptMuseumTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ConfigurationContent(
                uiState = ConfigurationUiState(fontScale, category),
                actions = ConfigurationUiActions(
                    onFontScaleChange = { fontScale = it },
                    onCategorySelected = { category = it }
                )
            )
        }
    }
}
