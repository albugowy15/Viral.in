package com.bughowi.viralin.model

data class NewsHeadlines(
    val source: Source? = null,
    val author: String? = null,
    val title: String,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String?= null,
    val content: String?= null
)