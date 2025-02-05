package com.example.assignment.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.R
import com.example.assignment.adapters.NewsAdapter
import com.example.assignment.base.BaseFragment
import com.example.assignment.databinding.FragmentSavedNewsBinding
import com.example.assignment.viewmodel.SavedNewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment : BaseFragment<FragmentSavedNewsBinding, SavedNewsViewModel>(R.layout.fragment_saved_news) {

    override val viewModel: SavedNewsViewModel by viewModels()
    private var newsAdapter: NewsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSavedNews()
        setRecyclerView()
        observeData()
        newsAdapter?.setOnDeleteClickListener{
            viewModel.deleteNews(newsAdapter?.newsList?.get(it)?.title)
            val currentList = newsAdapter?.newsList?.toMutableList()
            currentList?.removeAt(it)
            if (currentList != null) {
                newsAdapter?.newsList = currentList
            }
        }
    }

    private fun observeData() {
        viewModel.savedNewsList.observe(viewLifecycleOwner){
            newsAdapter?.newsList = it
        }
    }

    private fun setRecyclerView() {
        newsAdapter = NewsAdapter(true)
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

}