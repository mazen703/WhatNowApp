package com.example.newsapp.data

import com.google.gson.annotations.SerializedName

data class News(val articles:List<Article>)

data class Article(
    val title: String,
    val url: String,
    @SerializedName("urlToImage") val imageUrl: String?
)
