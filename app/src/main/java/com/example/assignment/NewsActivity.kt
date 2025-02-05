package com.example.assignment

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.assignment.base.BaseActivity
import com.example.assignment.databinding.ActivityNewsBinding
import com.example.assignment.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : BaseActivity<ActivityNewsBinding, NewsViewModel>(R.layout.activity_news) {

    override val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController( R.id.nav_host_fragment_container)
        val bottomNavigationView = binding.btmNav
        bottomNavigationView.setupWithNavController(navController)
    }


}