package com.mvvm.posts.ui.post.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.posts.R
import com.mvvm.posts.common.base.fragment.BaseViewModelFragment
import com.mvvm.posts.ui.post.list.PostsViewModel.PostsState.*
import com.mvvm.posts.util.ext.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_posts_list.*
import kotlinx.android.synthetic.main.layout_app_header.*
import timber.log.Timber

@AndroidEntryPoint
class PostsFragment : BaseViewModelFragment() {

    override val viewModel: PostsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts_list, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtHeaderTitle.text = getString(R.string.posts)
    }

    override fun observeUi() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer{ state ->
            when (state) {
                is Loading -> {
                    showLoading()
                }
                is Success ->{
                    val layoutManager = LinearLayoutManager(requireContext())
                    val cityAdapter = PostsAdapter(requireContext(), state.posts
                    ) {
                        findNavController().navigate(
                            PostsFragmentDirections.actionPostsFragmentToPostDetailFragment(it)
                        )
                    }
                    rcvPosts.layoutManager = layoutManager
                    rcvPosts.adapter = cityAdapter
                    hideLoading()
                    txtNoPosts.isVisible = false

                }
                is Error -> {
                    txtNoPosts.isVisible = true
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