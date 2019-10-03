package com.example.search.ui

import android.os.Bundle
import com.example.common.base.BaseActivity
import com.example.search.R
import com.example.search.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(SearchViewModel::class)

        binding.tv.setOnClickListener {
            viewModel.searchRepositories("PaymentCalculate")
        }
    }
}