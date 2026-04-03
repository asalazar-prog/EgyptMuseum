package com.asalazar.egyptmuseum.ui.discovery

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asalazar.egyptmuseum.domain.discovery.model.Category
import com.asalazar.egyptmuseum.ui.theme.EgyptMuseumTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DiscoveryScreen(
    discoveryViewModel: DiscoveryViewModel = koinViewModel()
) {
    val categories by discoveryViewModel.uiState
        .collectAsStateWithLifecycle()

    DiscoveryScreenContent(categories)
}

@Composable
private fun DiscoveryScreenContent(
    categories: List<Category>
) {

    LazyColumn {
        items(categories, key = { it.id }) { category ->
            CategoryCard(
                category,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 1f)
            ) { }
        }
    }


}

@Preview
@Composable
private fun DiscoveryContentPreview() {
    EgyptMuseumTheme {
        DiscoveryScreenContent(emptyList())
    }
}
