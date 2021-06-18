package com.mvvm.News.ui.News.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.News.R
import com.mvvm.News.common.base.fragment.BaseViewModelFragment
import com.mvvm.News.data.StateData
import com.mvvm.News.util.ext.showErrorDialog
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
        txtHeaderTitle.text = getString(R.string.News)
    }

    override fun observeUi() {

        viewModel.NewsLive.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it.status == StateData.DataStatus.LOADING){
                    showLoading()
                }
                when (it.status) {
                    StateData.DataStatus.SUCCESS -> {
                        if (it.data != null && it.data.isNotEmpty()) {
                            val layoutManager = LinearLayoutManager(requireContext())
                            val cityAdapter = NewsAdapter(requireContext(), it.data
                            ) {
                                findNavController().navigate(
                                        NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(it)
                                )
                            }
                            rcvNews.layoutManager = layoutManager
                            rcvNews.adapter = cityAdapter
                            hideLoading()
                            txtNoNews.isVisible = false
                        } else {
                            Timber.d("NOT FOUND")
                            txtNoNews.isVisible = true
                            hideLoading()
                        }
                    }
                    StateData.DataStatus.ERROR -> {
                        Timber.d(it.data.toString())
                        txtNoNews.isVisible = true
                        hideLoading()
                        Timber.d("Error .. . . ")
                        showErrorDialog("Error")
                    }
                }
            }
        })

    }

    override fun setOnClickListeners() {

    }
}