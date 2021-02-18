package com.mvvm.posts.ui.post.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.posts.domain.model.Post

@BindingAdapter("app:posts")
fun posts(
    listView : RecyclerView,
    items : List<Post>?
) {
    (listView.adapter as PostsAdapter).submitList(items)
}
