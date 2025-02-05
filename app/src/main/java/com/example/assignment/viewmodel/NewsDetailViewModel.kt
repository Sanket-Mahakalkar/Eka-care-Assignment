package com.example.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignment.base.BaseViewModel
import com.example.assignment.model.Article
import com.example.assignment.repo.NewsDetailRepo
import com.example.assignment.repo.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    newsDetailRepo: NewsDetailRepo
): BaseViewModel<NewsDetailRepo>(newsDetailRepo) {

    private var article: Article? = null

    private val _onSavingNews = MutableLiveData<Boolean>()
    val onSavingNews: LiveData<Boolean> = _onSavingNews

    fun setArticle(article: Article?) {
        this.article = article
    }

    fun saveArticle() {
        viewModelScope.launch {
            article?.let {
                it.isFav = true
                getRepo().saveArticle(it)
                _onSavingNews.value = true
            }

        }
    }
}