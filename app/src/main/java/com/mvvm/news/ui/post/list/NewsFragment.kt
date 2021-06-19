package com.mvvm.news.ui.News.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.news.R
import com.mvvm.news.common.base.fragment.BaseViewModelFragment
import com.mvvm.news.common.state.UiState
import com.mvvm.news.ui.News.list.NewsViewModel.NewsState.*
import com.mvvm.news.util.ext.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.layout_app_header.*
import timber.log.Timber

@AndroidEntryPoint
class NewsFragment : BaseViewModelFragment() {

    override val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtHeaderTitle.text = getString(R.string.Newss)
    }

    override fun observeUi() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer{ state ->
            when (state) {
                is UiState.Loading -> {
                    showLoading()
                }
                is UiState.Success ->{
                    Timber.d(".data.totalResul:::::::::::+${state.data.totalResults}")
                    val layoutManager = LinearLayoutManager(requireContext())
                    val cityAdapter = NewsAdapter(requireContext(), state.data.articles
                    ) {
                        findNavController().navigate(
                            NewsFragmentDirections.actionNewssFragmentToNewsDetailFragment(it)
                        )
                    }
                    rcvNews.layoutManager = layoutManager
                    rcvNews.adapter = cityAdapter
                    hideLoading()
                    txtNoNews.isVisible = false

                }
                is UiState.Error -> {
                    txtNoNews.isVisible = true
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