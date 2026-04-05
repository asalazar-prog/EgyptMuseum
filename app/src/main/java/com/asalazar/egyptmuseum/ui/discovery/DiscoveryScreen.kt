package com.asalazar.egyptmuseum.ui.discovery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asalazar.egyptmuseum.domain.discovery.model.Category
import com.asalazar.egyptmuseum.domain.discovery.model.CategoryType
import com.asalazar.egyptmuseum.ui.theme.EgyptMuseumTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DiscoveryScreen(
    onBackPressed: () -> Unit,
    onCategorySelected: (categoryId: CategoryType) -> Unit,
    discoveryViewModel: DiscoveryViewModel = koinViewModel()
) {
    val categories by discoveryViewModel.uiState
        .collectAsStateWithLifecycle()

    DiscoveryScreenContent(
        categories = categories,
        onBackPressed = onBackPressed,
        onCategorySelected = onCategorySelected
    )
}

@Composable
private fun DiscoveryScreenContent(
    categories: List<Category>,
    onBackPressed: () -> Unit,
    onCategorySelected: (CategoryType) -> Unit
) {
    ScaffoldDiscovery(onBackPressed) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(categories, key = { it.id }) { category ->
                CategoryCard(
                    category,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2 / 1f)
                ) { category.id.let(onCategorySelected) }
            }
        }
    }
}

@Composable
fun ScaffoldDiscovery(
    onBackPressed: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = { TopBarDiscovery(onBackPressed) },
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarDiscovery(
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = { Text("Descubrimiento") },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    Icons.AutoMirrored.Sharp.ArrowBack,
                    contentDescription = "Regresar"
                )
            }
        }
    )
}

@Preview
@Composable
private fun DiscoveryContentPreview() {
    EgyptMuseumTheme {
        DiscoveryScreenContent(
            emptyList(),
            {},
            {})
    }
}
