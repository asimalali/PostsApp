package com.stc.posts.ui.post.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.stc.posts.R
import com.stc.posts.common.base.fragment.BaseViewModelFragment
import com.stc.posts.databinding.FragmentPostsListBinding
import com.stc.posts.ui.post.list.PostsViewModel.*
import com.stc.posts.ui.post.list.PostsViewModel.PostsState.*
import com.stc.posts.util.ext.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_posts_list.*
import kotlinx.android.synthetic.main.layout_app_header.*
import timber.log.Timber

@AndroidEntryPoint
class PostsFragment : BaseViewModelFragment() {

    override val viewModel: PostsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_posts_list, container, false)
        FragmentPostsListBinding.bind(root).apply {
            lifecycleOwner = this@PostsFragment
            viewmodel = viewModel
        }
        return root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtHeaderTitle.text = "Posts"
        rcvPosts.adapter = PostsAdapter(viewModel) {
            findNavController().navigate(
                PostsFragmentDirections.actionPostsFragmentToPostDetailFragment(it)
            )
        }
    }

    override fun observeUi() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer{ state ->
            when (state) {
                is Loading -> {
                    showLoading()
                }
                is HideLoading -> {
                    hideLoading()
                }
                is Error -> {
                    hideLoading()
                    Timber.d("Error .. . . ")
                    showErrorDialog(state.error.message)
                }
            }
        })

    }



    override fun setOnClickListeners() {

    }
}