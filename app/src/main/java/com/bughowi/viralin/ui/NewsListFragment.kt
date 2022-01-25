package com.bughowi.viralin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bughowi.viralin.R
import com.bughowi.viralin.databinding.FragmentNewsListBinding

class NewsListFragment : Fragment() {

    private val viewModel : NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentNewsListBinding.inflate(inflater)
        viewModel.getArticleList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rvNewsList.adapter = NewsListAdapter(NewsListener { article ->
            viewModel.onArticleClicked(article)
            findNavController().navigate(R.id.action_newsListFragment_to_newsDetailFragment)
        })
        return binding.root
    }

}