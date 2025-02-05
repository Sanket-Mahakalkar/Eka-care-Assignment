package com.example.assignment.viewmodel

import com.example.assignment.base.BaseViewModel
import com.example.assignment.repo.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    newsRepo: NewsRepo
): BaseViewModel<NewsRepo>(newsRepo) {
}