package com.bughowi.viralin.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bughowi.viralin.R
import com.bughowi.viralin.databinding.FragmentNewsDetailBinding
import com.bumptech.glide.Glide

class NewsDetailFragment : Fragment() {

    private val viewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentNewsDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        Glide.with(this).load(viewModel.article.value?.urlToImage).placeholder(R.drawable.ic_baseline_broken_image_24).into(binding.imageNewsThumbnail)
        binding.buttonFullNews.setOnClickListener{
            val webUrl = viewModel.article.value?.url
            if (webUrl == null) {
                Toast.makeText(binding.root.context, "Website URL is not found", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(webUrl)
                startActivity(intent)
            }
        }
        return binding.root
    }
}