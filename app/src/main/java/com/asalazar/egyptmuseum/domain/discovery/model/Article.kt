package com.asalazar.egyptmuseum.domain.discovery.model

data class Article(
    val id: String,
    val title: String,
    val description: String,
    val coverImageUrl: String,
    val media: MediaContent
)
