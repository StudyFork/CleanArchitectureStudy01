package gong.team.githubclean.ui.profile

import android.os.Bundle
import gong.team.githubclean.R
import gong.team.githubclean.databinding.ActivityProfileBinding
import gong.team.githubclean.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity: BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    private val profileVIewModel: ProfileVIewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            profileVM = profileVIewModel
        }

    }
}