package com.mvvm.news.ui.News.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.news.R
import com.mvvm.news.domain.model.News
import com.mvvm.news.domain.model.NewsArticle
import com.mvvm.news.domain.model.NewsResponse
import com.mvvm.news.util.ext.setImageFromUrl

class NewsAdapter(
    private val context: Context,
    private val items: List<NewsArticle>,
    private val doOnClick : (NewsItem : NewsArticle) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView: View =
            LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder

        val item = items[position]

        viewHolder.NewsTitle.text = item.title
        item.urlToImage?.let { viewHolder.image.setImageFromUrl(it) }
        viewHolder.NewsTitle.setOnClickListener { doOnClick(item) }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var NewsTitle: TextView = itemView.findViewById(R.id.NewsTitle)
        var image: ImageView = itemView.findViewById(R.id.imageArticle)
    }
}


