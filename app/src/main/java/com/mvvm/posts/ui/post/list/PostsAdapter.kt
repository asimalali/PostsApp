package com.mvvm.posts.ui.post.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.posts.R
import com.mvvm.posts.domain.model.Post

class PostsAdapter(
    private val context: Context,
    private val items: List<Post>?,
    private val doOnClick : (postItem : Post) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView: View =
            LayoutInflater.from(context).inflate(R.layout.item_posts, parent, false)
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

        viewHolder.postTitle.text = item?.title
        viewHolder.postTitle.setOnClickListener {
            if (item != null) {
                doOnClick(item)
            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postTitle: TextView = itemView.findViewById(R.id.postTitle)
    }
}


