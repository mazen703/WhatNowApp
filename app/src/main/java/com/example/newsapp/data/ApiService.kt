package com.example.newsapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
        @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("category") category: String,
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String ="eb13fd19b10c4746b2aa5f31269606c9"
    ): News




}