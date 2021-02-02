package com.stc.posts.ui.post.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stc.posts.R
import com.stc.posts.common.base.fragment.BaseFragment
import com.stc.posts.common.base.fragment.BaseViewModelFragment
import com.stc.posts.ui.post.list.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_post_detail.*
import kotlinx.android.synthetic.main.layout_app_header.*


@AndroidEntryPoint
class PostDetailFragment  : BaseFragment(){

    private val args :  PostDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        return inflater.inflate(R.layout.fragment_post_detail, container, false)
    }


    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnHeaderBack.isVisible = true
        setTexts()
    }

    private fun setTexts(){
        txtHeaderTitle.text = getString(R.string.post_detail)
        args.post.apply {
            txtId.text = id.toString()
            txtBody.text = body
            txtUserId.text = userId.toString()
            txtTitle.text = title
        }
    }


    override fun setOnClickListeners() {
        btnHeaderBack.setOnClickListener { findNavController().navigateUp() }
    }

}