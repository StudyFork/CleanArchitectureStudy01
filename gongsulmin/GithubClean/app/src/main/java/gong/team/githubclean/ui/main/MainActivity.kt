package gong.team.githubclean.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import gong.team.githubclean.R
import gong.team.githubclean.adapter.MainAdapter
import gong.team.githubclean.databinding.ActivityMainBinding
import gong.team.githubclean.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            mainVM = mainViewModel
            recyclerview.adapter = MainAdapter()
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}

