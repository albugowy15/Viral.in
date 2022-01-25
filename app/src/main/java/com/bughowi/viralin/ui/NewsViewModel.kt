package com.bughowi.viralin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bughowi.viralin.model.NewsHeadlines
import com.bughowi.viralin.network.NewsApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class NewsListStatus {LOADING, DONE, FAILED}
class NewsViewModel: ViewModel() {

    private val _articles = MutableLiveData<List<NewsHeadlines>>()
    val articles: LiveData<List<NewsHeadlines>> = _articles

    private val _article = MutableLiveData<NewsHeadlines>()
    val article: LiveData<NewsHeadlines> = _article

    private val _status = MutableLiveData<NewsListStatus>()
    val status : LiveData<NewsListStatus> = _status

    private val _category = MutableLiveData("general")
    val category: LiveData<String> = _category

    private var previousCategory: String = "general"

    fun getArticleList() {
        viewModelScope.launch {
            _status.value = NewsListStatus.LOADING
            try {
                _articles.value = NewsApi.retrofitService.getArticles("us", _category.value.toString(), "cb7d0bebc6194fdab5019b46f77667fd").articles
                _status.value = NewsListStatus.DONE
            } catch (e: Exception) {
                _articles.value = listOf()
                _status.value = NewsListStatus.FAILED
                e.printStackTrace()
            }
        }
    }

    fun onArticleClicked(article: NewsHeadlines) {
        _article.value = article
    }

    fun setCategory(category: String) {
        previousCategory = _category.value.toString()
        if (previousCategory != category) {
            previousCategory = _category.value.toString()
            _category.value = category
            getArticleList()
        }
    }

}