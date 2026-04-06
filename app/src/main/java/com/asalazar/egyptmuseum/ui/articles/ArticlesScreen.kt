package com.asalazar.egyptmuseum.ui.articles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.asalazar.egyptmuseum.R
import com.asalazar.egyptmuseum.data.discovery.MockEgyptRepository
import com.asalazar.egyptmuseum.domain.discovery.model.Article
import com.asalazar.egyptmuseum.domain.discovery.model.CategoryType
import com.asalazar.egyptmuseum.ui.theme.EgyptMuseumTheme
import com.asalazar.egyptmuseum.ui.theme.component.MuseumHorizontalDivider
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticlesScreen(
    articlesViewModel: ArticlesViewModel = koinViewModel(),
    onArticleSelected: (Int, CategoryType) -> Unit
) {
    val category by articlesViewModel.currentCategory.collectAsStateWithLifecycle()

    category ?: return

    ArticlesScreenContent(
        category!!.id,
        articles = category!!.articles,
        onUpdateCategory = articlesViewModel::updateCategory
    ) {
        onArticleSelected(it, category!!.id)
    }
}

@Composable
private fun ArticlesScreenContent(
    currentCategory: CategoryType,
    articles: List<Article>,
    onUpdateCategory: (CategoryType) -> Unit,
    onArticleSelected: (Int) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        stickyHeader {
            CategoryTabRow(currentCategory, onTabSelected = onUpdateCategory)
        }

        items(articles) { article ->
            ArticleItem(article, modifier = Modifier.padding(horizontal = 8.dp)) {
                onArticleSelected(article.id)
            }
        }
    }
}

@Composable
fun ArticleItem(
    article: Article,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        onClick = onClick
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.coverImageUrl)
                .crossfade(true)
                .build(),
            contentDescription = article.coverDescriptionImage,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                article.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )

            MuseumHorizontalDivider(modifier = Modifier.fillMaxWidth(0.6f))

            Text(
                article.description,
                modifier = Modifier
                    .padding(8.dp)
            )
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryTabRow(
    currentCategory: CategoryType,
    modifier: Modifier = Modifier,
    categories: Array<CategoryType> = CategoryType.entries.toTypedArray(),
    onTabSelected: (CategoryType) -> Unit
) {
    PrimaryTabRow(
        categories.indexOf(currentCategory),
        modifier = modifier,
    ) {
        categories.forEachIndexed { index, type ->
            Tab(
                selected = type == currentCategory,
                onClick = { type.let(onTabSelected) },
                text = { Text(type.label(), maxLines = 2, overflow = TextOverflow.Ellipsis) }
            )
        }
    }
}

@Composable
fun CategoryType.label(): String = when (this) {
    CategoryType.LIFE -> stringResource(R.string.lbl_life_category)
    CategoryType.ARCHITECTURE -> stringResource(R.string.lbl_architecture_category)
    CategoryType.ART -> stringResource(R.string.lbl_art_category)
}

@Preview
@Composable
private fun ArticlesScreenPreview() {
    val mockData = MockEgyptRepository()
        .getCategoryById(CategoryType.ARCHITECTURE)
        ?.articles

    EgyptMuseumTheme {
        ArticlesScreenContent(
            currentCategory = CategoryType.ARCHITECTURE,
            mockData ?: listOf(),
            {}
        ) {}
    }
}
