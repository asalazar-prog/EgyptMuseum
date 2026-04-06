package com.asalazar.egyptmuseum.domain.discovery.model

data class Article(
    val id: Int,
    val title: String,
    val description: String,
    val coverImageUrl: String,
    val media: MediaContent,
    val coverDescriptionImage: String? = null
)
