package com.mvvm.News.ui.News.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mvvm.News.R
import com.mvvm.News.common.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news_detail.*
import kotlinx.android.synthetic.main.layout_app_header.*


@AndroidEntryPoint
class NewsDetailFragment  : BaseFragment(){

    private val args :  NewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }


    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnHeaderBack.isVisible = true
        setTexts()
    }

    private fun setTexts(){
        txtHeaderTitle.text = getString(R.string.News_detail)
        args.News.apply {
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