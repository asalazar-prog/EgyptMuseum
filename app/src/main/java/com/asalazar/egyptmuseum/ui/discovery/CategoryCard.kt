package com.asalazar.egyptmuseum.ui.discovery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.asalazar.egyptmuseum.R
import com.asalazar.egyptmuseum.domain.discovery.model.Category
import com.asalazar.egyptmuseum.domain.discovery.model.CategoryType
import com.asalazar.egyptmuseum.ui.theme.EgyptMuseumTheme

private val WarmGradientBackground: Brush
    @Composable
    get() = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            MaterialTheme.colorScheme.secondaryContainer
        )
    )

@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier,
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(category.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(R.drawable.placeholder)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(WarmGradientBackground)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                TitleText(category.title)
                SubtitleText(category.subtitle)
            }

        }
    }
}

@Composable
private fun TitleText(title: String, modifier: Modifier = Modifier) {
    Text(
        title,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        modifier = modifier
    )
}

@Composable
private fun SubtitleText(subtitle: String, modifier: Modifier = Modifier) {
    Text(
        subtitle,
        color = Color.DarkGray,
        modifier = modifier
    )
}

@Preview
@Composable
private fun CategoryCardPreview() {
    val categoryMock = remember {
        Category(
            id = CategoryType.ART,
            title = "Arte",
            subtitle = "Esculturas, frascos y la belleza de lo sagrado.",
            imageUrl = "https://raw.githubusercontent.com/asalazar-prog/EgyptMuseum/assets/images/egypt_architecture_category.webp",
            articles = listOf()
        )
    }

    EgyptMuseumTheme {
        CategoryCard(
            categoryMock,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 1f)
        ) { }
    }
}
