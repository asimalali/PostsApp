package com.mvvm.posts.ui.post.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.posts.domain.model.Post
import com.mvvm.posts.databinding.ItemPostsBinding
import timber.log.Timber

class PostsAdapter(
private val viewModel : PostsViewModel,
private val doOnClick : (postItem : Post) -> Unit
) : ListAdapter<Post, PostsAdapter.ViewHolder>(PostsDiffCallback()) {

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) = ViewHolder(
        ItemPostsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder : ViewHolder,
        position : Int
    ) = holder.bind(
        viewModel,
        getItem(position),
        doOnClick
    )

    inner class ViewHolder(private val binding : ItemPostsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            viewModel : PostsViewModel,
            item : Post,
            doOnClick : (post : Post) -> Unit
        ) = with(binding) {
            Timber.d("123456")
            viewmodel = viewModel
            post = item
            itemView.setOnClickListener { doOnClick(item) }
        }
    }
}


class PostsDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem : Post, newItem : Post) =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem : Post, newItem : Post) =
        oldItem == newItem
}