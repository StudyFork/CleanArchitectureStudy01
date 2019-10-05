package com.example.filetree

import android.os.Bundle
import com.example.common.base.BaseActivity
import com.example.filetree.databinding.ActivityFileTreeBinding

class FileTreeActivity : BaseActivity<ActivityFileTreeBinding>(R.layout.activity_file_tree) {

    private lateinit var viewModel: FileTreeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(FileTreeViewModel::class)
        binding.vm = viewModel

        setUpView()
        observeViewModel()
    }

    private fun setUpView() {
    }

    private fun observeViewModel() {
    }
}