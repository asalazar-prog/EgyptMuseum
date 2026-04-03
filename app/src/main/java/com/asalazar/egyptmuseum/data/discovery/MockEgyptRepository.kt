package com.asalazar.egyptmuseum.data.discovery

import com.asalazar.egyptmuseum.domain.discovery.model.Article
import com.asalazar.egyptmuseum.domain.discovery.model.Category
import com.asalazar.egyptmuseum.domain.discovery.model.CategoryType
import com.asalazar.egyptmuseum.domain.discovery.model.MediaContent

class MockEgyptRepository : EgyptRepository {

    private val baseUrl = "https://raw.githubusercontent.com/asalazar-prog/EgyptMuseum/assets/images"

    private val categories = listOf(
        Category(
            id = CategoryType.VIDA_COTIDIANA,
            title = "Vida Cotidiana",
            subtitle = "Comercio, familia y tradiciones a orillas del Nilo.",
            imageUrl = "$baseUrl/egypt_life_category.webp",
            articles = createMockArticles("Vida", CategoryType.VIDA_COTIDIANA)
        ),
        Category(
            id = CategoryType.ARQUITECTURA,
            title = "Arquitectura",
            subtitle = "Monumentos eternos y la ingeniería de los dioses.",
            imageUrl = "$baseUrl/egypt_architecture_category.webp",
            articles = createMockArticles("Arq", CategoryType.ARQUITECTURA)
        ),
        Category(
            id = CategoryType.ARTE,
            title = "Arte",
            subtitle = "Esculturas, frescos y la belleza de lo sagrado.",
            imageUrl = "$baseUrl/egypt_art_category.webp",
            articles = createMockArticles("Arte", CategoryType.ARTE)
        )
    )

    override fun getCategories(): List<Category> = categories

    override fun getCategoryById(id: CategoryType): Category? {
        return categories.find { it.id == id }
    }

    private fun createMockArticles(prefix: String, type: CategoryType): List<Article> {
        return (1..5).map { index ->
            Article(
                id = "${type.name}_$index",
                title = "$prefix - Hallazgo #$index",
                description = "Una descripción detallada sobre este descubrimiento en el Antiguo Egipto.",
                imageUrl = "$baseUrl/article_placeholder.webp",
                media = MediaContent(
                    videoUrl = "https://www.ejemplo.com/video_$index.mp4",
                    audioUrl = "https://www.ejemplo.com/audio_$index.mp3",
                    duration = "03:45"
                )
            )
        }
    }
}
