package com.example.filetree

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.common.base.BaseActivity
import com.example.filetree.databinding.ActivityFileTreeBinding
import com.example.navigator.FileTreeNavigator
import com.example.navigator.FileTreeNavigatorConstants
import javax.inject.Inject

class FileTreeActivity : BaseActivity<ActivityFileTreeBinding>(R.layout.activity_file_tree) {

    private lateinit var viewModel: FileTreeViewModel

    private lateinit var owner: String
    private lateinit var repoName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpExtra()

        viewModel = getViewModel(FileTreeViewModel::class)
        binding.vm = viewModel

        setUpView()
        observeViewModel()
    }

    private fun setUpExtra() {
        intent?.run {
            this@FileTreeActivity.owner = getStringExtra(FileTreeNavigatorConstants.OWNER)
            this@FileTreeActivity.repoName =
                getStringExtra(FileTreeNavigatorConstants.REPOSITORY_NAME)
        }
    }

    private fun setUpView() {
        viewModel.getContentsInRoot(owner, repoName)
    }

    private fun observeViewModel() {
    }
}

class FileTreeNavigatorImpl @Inject constructor(
    private val activity: Activity
) : FileTreeNavigator {

    override fun startFileTree(owner: String, repoName: String) {
        activity.startActivity(Intent(activity, FileTreeActivity::class.java).apply {
            putExtra(FileTreeNavigatorConstants.OWNER, owner)
            putExtra(FileTreeNavigatorConstants.REPOSITORY_NAME, repoName)
        })
    }
}