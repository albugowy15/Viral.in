package com.bughowi.viralin.model

data class NewsApiResponse (
    val status: String,
    val totalResults: Int,
    val articles: List<NewsHeadlines>
)