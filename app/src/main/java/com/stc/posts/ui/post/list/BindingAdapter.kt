package com.stc.posts.ui.post.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stc.posts.domain.model.Post

@BindingAdapter("app:posts")
fun posts(
    listView : RecyclerView,
    items : List<Post>?
) {
    (listView.adapter as PostsAdapter).submitList(items)
}
