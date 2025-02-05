package com.example.assignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignment.base.BaseViewModel
import com.example.assignment.model.Article
import com.example.assignment.repo.SavedNewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(savedNewsRepo: SavedNewsRepo): BaseViewModel<SavedNewsRepo>(savedNewsRepo) {
    private val _savedNewsList = MutableLiveData<List<Article>>()
    val savedNewsList: MutableLiveData<List<Article>> = _savedNewsList

    fun getSavedNews() {
        viewModelScope.launch {
            getRepo().getSavedNews().flowOn(getRepo().getDispatcher().io()).collectLatest {
                _savedNewsList.value = it
            }

        }
    }

    fun deleteNews(title: String?) {
        viewModelScope.launch {
            getRepo().deleteNews(title)
        }
    }
}