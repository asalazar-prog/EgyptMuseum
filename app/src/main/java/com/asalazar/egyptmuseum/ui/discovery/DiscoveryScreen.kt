package com.asalazar.egyptmuseum.ui.discovery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    onCategorySelected: (categoryId: CategoryType) -> Unit,
    discoveryViewModel: DiscoveryViewModel = koinViewModel()
) {
    val categories by discoveryViewModel.uiState
        .collectAsStateWithLifecycle()

    DiscoveryScreenContent(
        categories = categories,
        onCategorySelected = onCategorySelected
    )
}

@Composable
private fun DiscoveryScreenContent(
    categories: List<Category>,
    onCategorySelected: (CategoryType) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
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

@Preview
@Composable
private fun DiscoveryContentPreview() {
    EgyptMuseumTheme {
        DiscoveryScreenContent(
            emptyList(),
            {})
    }
}
