package com.asalazar.egyptmuseum.ui.configuration

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asalazar.egyptmuseum.ui.theme.EgyptMuseumTheme

@Composable
fun ConfigurationScreen() {

    val viewModel: ConfigurationViewModel =
        remember { ConfigurationViewModel() } /*TODO: Implement factory*/

    val fontScale by viewModel.fontScale.collectAsStateWithLifecycle()
    val ageCategory by viewModel.ageCategory.collectAsStateWithLifecycle()

    ConfigurationContent(
        fontScale = fontScale,
        selectedCategory = ageCategory,
        onFontScaleChange = viewModel::updateFontScale,
        onCategorySelected = viewModel::updateAgeCategory
    )
}

@Composable
private fun ConfigurationContent(
    fontScale: Float,
    selectedCategory: AgeCategory,
    onFontScaleChange: (Float) -> Unit,
    onCategorySelected: (AgeCategory) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ConfigurationTopBar() }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FontSizeCard(fontScale, onValueChange = onFontScaleChange)
            AgeFilterCard(
                selectedCategory = selectedCategory,
                onCategorySelected = onCategorySelected
            )
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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ConfigurationContentPreview() {
    var selectedCategory by remember { mutableStateOf(AgeCategory.TEENS) }
    var fontScale by remember { mutableStateOf(0.8f) }
    EgyptMuseumTheme {
        ConfigurationContent(
            fontScale = fontScale,
            selectedCategory = selectedCategory,
            onFontScaleChange = { fontScale = it },
            onCategorySelected = { selectedCategory = it })
    }
}
