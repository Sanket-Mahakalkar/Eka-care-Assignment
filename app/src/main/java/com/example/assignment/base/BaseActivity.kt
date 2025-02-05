package com.example.assignment.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding, VM: BaseViewModel<*>>
    (private val layout: Int): AppCompatActivity() {

    abstract val viewModel: VM

    private lateinit var _binding: VB

    protected val binding: VB
        get() =  _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this,layout)
    }
}