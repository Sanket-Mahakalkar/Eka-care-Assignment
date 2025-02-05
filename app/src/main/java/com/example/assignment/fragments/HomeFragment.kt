package com.example.assignment.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.NewsDetailActivity
import com.example.assignment.R
import com.example.assignment.adapters.NewsAdapter
import com.example.assignment.base.BaseFragment
import com.example.assignment.databinding.FragmentHomeBinding
import com.example.assignment.model.Article
import com.example.assignment.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()
    private var newsAdapter: NewsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        observeData()
        newsAdapter?.setOnReadMoreClickListener {
            val intent = Intent(requireActivity(), NewsDetailActivity::class.java).apply {
                putExtra("article", it)
            }
            newsDetailsLauncher.launch(intent)
        }
    }

    private val newsDetailsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        if(result.resultCode == Activity.RESULT_OK){
            val shouldOpenSaveFragment = result.data?.getBooleanExtra("shouldOpenSaveFragment", false)?:false
            if(shouldOpenSaveFragment){
                findNavController().navigate(R.id.savedNewsFragment)
            }
        }

    }

    private fun observeData() {
        viewModel.newsList.observe(viewLifecycleOwner) { news ->
            news?.let {
                newsAdapter?.newsList = it
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            showToastMessage(it)
        }
    }

    private fun setRecyclerView() {
        newsAdapter = NewsAdapter(false)
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNews()
    }
}