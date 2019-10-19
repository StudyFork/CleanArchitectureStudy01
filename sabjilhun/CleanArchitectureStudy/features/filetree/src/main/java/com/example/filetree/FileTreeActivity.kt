package com.example.filetree

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.common.base.BaseActivity
import com.example.filetree.adapter.FileTreeAdapter
import com.example.filetree.databinding.ActivityFileTreeBinding
import com.example.navigator.FileTreeNavigator
import com.example.navigator.FileTreeNavigatorConstants
import javax.inject.Inject

class FileTreeActivity : BaseActivity<ActivityFileTreeBinding>(R.layout.activity_file_tree) {

    private lateinit var viewModel: FileTreeViewModel

    private lateinit var owner: String
    private lateinit var repoName: String

    private var fileTreeAdapter: FileTreeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpExtra()

        viewModel = getViewModel(FileTreeViewModel::class)
        binding.vm = viewModel

        setUpView()
        observeViewModel()
    }

    private fun setUpExtra() {
        intent?.apply {
            this@FileTreeActivity.owner = getStringExtra(FileTreeNavigatorConstants.OWNER)
            this@FileTreeActivity.repoName =
                getStringExtra(FileTreeNavigatorConstants.REPOSITORY_NAME)
        }
    }

    private fun setUpView() {
        viewModel.setRepoTitle(owner, repoName)
        viewModel.getContentsInRoot(owner, repoName)
    }

    private fun observeViewModel() {
        viewModel.fileTree.observe(this, Observer {
            if (it == null) {
                return@Observer
            }

            if (fileTreeAdapter == null) {
                fileTreeAdapter = FileTreeAdapter(it.second)
                binding.rvFileTree.adapter = fileTreeAdapter
            }

            fileTreeAdapter!!.treeNode = it.second
            fileTreeAdapter!!.expendFolder(it.first)
        })
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