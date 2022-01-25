package com.bughowi.viralin.network

import com.bughowi.viralin.model.NewsApiResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://newsapi.org/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getArticles(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") api_key: String): NewsApiResponse
//    @GET("top-headlines?country=us&apiKey=cb7d0bebc6194fdab5019b46f77667fd")
//    suspend fun getArticles(): NewsApiResponse
}

object NewsApi {
    val retrofitService : NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}
