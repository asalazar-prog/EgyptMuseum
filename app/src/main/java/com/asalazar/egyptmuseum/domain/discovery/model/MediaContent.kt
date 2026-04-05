package com.asalazar.egyptmuseum.domain.discovery.model

data class MediaContent(
    val videoUrl: String,
    val audioUrl: String,
    val imageCollection: List<String>
)
