package com.asalazar.egyptmuseum.ui.articles

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import com.asalazar.egyptmuseum.ui.theme.component.EgyptAudioComponent
import com.asalazar.egyptmuseum.ui.theme.component.EgyptVideoPlayer
import com.asalazar.egyptmuseum.ui.theme.component.MuseumHorizontalDivider
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticleDetailScreen(
    articleDetailViewModel: ArticleDetailViewModel = koinViewModel()
) {
    val article by articleDetailViewModel.article.collectAsStateWithLifecycle()
    article ?: return
    ArticleDetailScreenContent(article!!)
}

@Composable
private fun ArticleDetailScreenContent(article: Article) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            ImageRequest.Builder(context)
                .data(article.coverImageUrl)
                .crossfade(true)
                .build(),
            contentDescription = article.coverDescriptionImage
        )
        Text(article.title, style = MaterialTheme.typography.displaySmall)
        MuseumHorizontalDivider(modifier = Modifier.fillMaxWidth(0.8f))

        EgyptAudioComponent(Uri.parse("android.resource://${context.packageName}/${R.raw.audio_example}"))

        Text(article.description)
        Spacer(Modifier.padding(8.dp))
        BodyArticle()

        Spacer(Modifier.padding(8.dp))

        EgyptVideoPlayer(R.raw.egypt_short_720, modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(4/3f)
        )

        Spacer(Modifier.padding(8.dp))

        BodyArticle()

        AsyncImage(
            ImageRequest.Builder(LocalContext.current)
                .data("https://content-historia.nationalgeographic.com.es/medio/2023/07/14/el-dios-solar-re-con-cabeza-de-halcon-acompanado-de-maat-diosa-de-la-justicia-y-el-orden-pintura-de-la-tumba-de-tausert-y-setnakht-en-el-valle-de-los-reyes_ce253200_230714095645_1280x720.jpg")
                .crossfade(true)
                .build(), contentDescription = null
        )
    }
}

@Composable
private fun BodyArticle(modifier: Modifier = Modifier) {
    Text(stringResource(R.string.lorem), modifier = modifier)
}

@Preview
@Composable
private fun ArticleDetailScreenPreview() {
    val mockEgyptRepository = MockEgyptRepository()
    EgyptMuseumTheme {
        ArticleDetailScreenContent(mockEgyptRepository.getArticle(1, CategoryType.ARCHITECTURE)!!)
    }
}
