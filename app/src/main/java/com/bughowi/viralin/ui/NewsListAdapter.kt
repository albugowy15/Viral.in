package com.bughowi.viralin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bughowi.viralin.R
import com.bughowi.viralin.databinding.ListItemNewsBinding
import com.bughowi.viralin.model.NewsHeadlines
import com.bumptech.glide.Glide

class NewsListAdapter(private val clickListener: NewsListener): ListAdapter<NewsHeadlines, NewsListAdapter.NewsViewHolder>(DiffCallback) {

    class NewsViewHolder(var binding: ListItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: NewsListener, article: NewsHeadlines) {
            Glide.with(binding.root).load(article.urlToImage).placeholder(R.drawable.ic_baseline_broken_image_24).into(binding.imageNewsThumbnail)
            binding.article = article
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<NewsHeadlines>() {
        override fun areItemsTheSame(oldItem: NewsHeadlines, newItem: NewsHeadlines): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: NewsHeadlines, newItem: NewsHeadlines): Boolean {
            return oldItem.title == newItem.title && oldItem.url == newItem.url && oldItem.content == newItem.content
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(ListItemNewsBinding.inflate(layoutInflater,parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(clickListener, article)
    }

}

class NewsListener(val clickListener: (article: NewsHeadlines) -> Unit) {
    fun onClick(article: NewsHeadlines) = clickListener(article)
}