package com.example.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignment.base.BaseViewModel
import com.example.assignment.data.network.Resource
import com.example.assignment.model.Article
import com.example.assignment.repo.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(homeRepo: HomeRepo): BaseViewModel<HomeRepo>(homeRepo) {

    private val _newsList = MutableLiveData<List<Article>>()
    val newsList: LiveData<List<Article>> = _newsList

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun getNews() {
        viewModelScope.launch {
            showLoading()
            getRepo().getNews().flowOn(getRepo().getDispatcher().io()).collectLatest {
                hideLoading()
                when(it){
                    is Resource.Success -> {
                        val articleList =  it.data?.articles?.map {
                            Article(   publishedAt = it.publishedAt,
                                author = it.author,
                                urlToImage = it.urlToImage,
                                description = it.description,
                                title = it.title,
                                url = it.url,
                                content = it.content)
                        }?: emptyList()
                        _newsList.value = articleList
                        getRepo().saveNews(articleList)
                    }
                    is Resource.DataError -> {
                        _newsList.value = getRepo().getNewsFromDb()
                        _errorMessage.value = it.apiError?.message
                    }
                }
            }
        }
    }
}