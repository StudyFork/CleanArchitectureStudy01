package gong.team.githubclean.ui.profile

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import gong.team.githubclean.R
import gong.team.githubclean.databinding.ActivityProfileBinding
import gong.team.githubclean.ui.base.BaseActivity
import gong.team.githubclean.ui.follow.FollowFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity: BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    private val profileVIewModel: ProfileVIewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            profileVM = profileVIewModel
            recyclerRepos.adapter = ReposAdapter()
            recyclerRepos.layoutManager = LinearLayoutManager(this@ProfileActivity)
        }

        profileVIewModel.navigateToFollow.observe(this , Observer {
            FollowFragment.newInstance(
                Bundle().apply {
                    putBoolean("KEY_IS_FOLLOWING" , it.peekContent().first)
                    putString("KEY_NAME" , it.peekContent().second)
                }
            ).show(supportFragmentManager , null)
        })

    }
}