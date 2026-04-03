package com.asalazar.egyptmuseum.domain.discovery.model

data class Category(
    val id: CategoryType,
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val articles: List<Article>
)
