package com.example.newsapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.Article
import com.example.newsapp.data.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repo = NewsRepository()

    private val _news = MutableLiveData<List<Article>>()
    val news: LiveData<List<Article>> = _news

    private val _error = MutableLiveData<String>()

    val error: MutableLiveData<String> = _error


    fun fetchNews(category: String) {
        viewModelScope.launch {
            Log.d("NEWS", "Fetching news...")
            try {
                val response = repo.getNews(category)
                _news.value = response.articles
            } catch (e: Exception) {

                _error.value=e.message
            }
        }
    }
}