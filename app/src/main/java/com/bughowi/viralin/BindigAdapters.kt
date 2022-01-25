package com.bughowi.viralin

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bughowi.viralin.model.NewsHeadlines
import com.bughowi.viralin.ui.NewsListAdapter
import com.bughowi.viralin.ui.NewsListStatus

@BindingAdapter("listData")
fun bindRecyclerView(rvNewsList: RecyclerView, data: List<NewsHeadlines>?) {
    val adapter = rvNewsList.adapter as NewsListAdapter
    adapter.submitList(data)
}

@BindingAdapter("cutText")
fun cutLongText(textView: TextView, data: String) {
    val shortString: String = if (data.length > 50) {
        data.substring(0, 30)
    } else {
        data
    }
    textView.text = shortString
}

@BindingAdapter("cutTitle")
fun cutLongTitle(textView: TextView, title: String) {
    val context: Context = textView.context

    if (title.length>=60) {
        textView.text = context.getString(R.string.long_text_replacement, title.substring(0,55))
    } else {
        textView.text = title
    }
}

@BindingAdapter("imgStatus")
fun imageStatus(img: ImageView, status: NewsListStatus) {
    when(status) {
        NewsListStatus.LOADING -> {
            img.visibility = View.VISIBLE
            img.setImageResource(R.drawable.loading_animation)}
        NewsListStatus.DONE -> {
            img.visibility = View.GONE
        }
        NewsListStatus.FAILED -> {
            img.visibility = View.VISIBLE
            img.setImageResource(R.drawable.ic_connection_error)
        }
    }
}