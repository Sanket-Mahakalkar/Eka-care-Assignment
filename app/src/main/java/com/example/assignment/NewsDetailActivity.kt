package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.viewModels
import com.example.assignment.base.BaseActivity
import com.example.assignment.databinding.ActivityNewsDetailBinding
import com.example.assignment.model.Article
import com.example.assignment.viewmodel.NewsDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailActivity : BaseActivity<ActivityNewsDetailBinding, NewsDetailViewModel>(R.layout.activity_news_detail) {

    override val viewModel: NewsDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val article = intent.getParcelableExtra<Article>("article")
        viewModel.setArticle(article)
        loadWebView(article?.url)
        observeData()
        binding.saveBtn.setOnClickListener {
            viewModel.saveArticle()
        }
    }

    private fun observeData() {
        viewModel.onSavingNews.observe(this) {
            val intent = Intent().apply {
                putExtra("shouldOpenSaveFragment", it)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun loadWebView(url: String?) {
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.apply {
            javaScriptEnabled = true // Enable JavaScript if needed
            domStorageEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
        }
        url?.let {
            binding.webView.loadUrl(it)
        }
    }
}