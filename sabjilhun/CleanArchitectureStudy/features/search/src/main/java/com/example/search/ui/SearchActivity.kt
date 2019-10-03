package com.example.search.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.example.common.base.BaseActivity
import com.example.search.R
import com.example.search.databinding.ActivitySearchBinding
import com.example.search.ui.adapter.RepositorySummaryInfoAdapter

class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(SearchViewModel::class)
        binding.vm = viewModel

        setUpView()
        observeViewModel()
    }

    private fun setUpView() {
        binding.rvSearchedImage.adapter = RepositorySummaryInfoAdapter()
        binding.etSearchKeyword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.searchRepositories()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun observeViewModel() {
        viewModel.alertEmptyQuery.observe(this, Observer {
            showSnackBar(getString(R.string.alert_empty_query))
        })
    }
}