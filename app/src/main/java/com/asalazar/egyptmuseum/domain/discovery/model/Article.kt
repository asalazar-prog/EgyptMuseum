package com.asalazar.egyptmuseum.domain.discovery.model

data class Article(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val media: MediaContent
)
