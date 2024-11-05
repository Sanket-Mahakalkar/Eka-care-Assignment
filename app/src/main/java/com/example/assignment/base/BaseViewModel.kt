package com.example.assignment.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<R: BaseRepository>(
    private val repository: R): ViewModel(){

    fun getRepo() = repository
    val showLoadingDialog = MutableLiveData(false)


    fun showLoading(){
        if(!showLoadingDialog.value!!){
            showLoadingDialog.value = true
        }
    }

    fun hideLoading(){
        if(showLoadingDialog.value!!){
            showLoadingDialog.value = false
        }
    }

}