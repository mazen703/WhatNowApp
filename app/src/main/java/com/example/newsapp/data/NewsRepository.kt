package com.example.newsapp.data

import com.example.newsapp.data.RetrofitInstance

class NewsRepository {
    private val api = RetrofitInstance.api

    suspend fun getNews(category: String) = api.getNews(category)
}