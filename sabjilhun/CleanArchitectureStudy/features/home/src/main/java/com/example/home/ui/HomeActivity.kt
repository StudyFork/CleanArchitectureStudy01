package com.example.home.ui

import android.os.Bundle
import com.example.common.base.BaseActivity
import com.example.home.R
import com.example.home.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(HomeViewModel::class)

        binding.tv.setOnClickListener {
            viewModel.test()
        }
    }
}