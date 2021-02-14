package com.stc.posts.ui.post.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stc.posts.R
import com.stc.posts.domain.model.Post
import com.stc.posts.databinding.ItemPostsBinding
import timber.log.Timber

class PostsAdapter(
    private val context: Context,
    private val items: List<Post>,
    private val doOnClick : (postItem : Post) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView: View =
            LayoutInflater.from(context).inflate(R.layout.item_posts, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder

        val item = items[position]

        viewHolder.postTitle.text = item.title
        viewHolder.postTitle.setOnClickListener { doOnClick(item) }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postTitle: TextView = itemView.findViewById(R.id.postTitle)
    }
}


