package com.mvvm.News.ui.News.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.News.R
import com.mvvm.News.domain.model.NewsItem

class NewsAdapter(
        private val context: Context,
        private val items: List<NewsItem>?,
        private val doOnClick : (NewsItemItem : NewsItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView: View =
            LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        if (items != null) {
            return items.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder

        val item = items?.get(position)

        viewHolder.NewsTitle.text = item?.title
        viewHolder.NewsTitle.setOnClickListener {
            if (item != null) {
                doOnClick(item)
            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var NewsTitle: TextView = itemView.findViewById(R.id.NewsTitle)
    }
}


