package gong.team.githubclean.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import gong.team.githubclean.R
import gong.team.githubclean.databinding.ActivityLoginBinding
import gong.team.githubclean.ui.base.BaseActivity
import gong.team.githubclean.ui.profile.ProfileActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            loginVM = loginViewModel
        }

        loginViewModel.navigateToMain.observe(this , Observer {
            it.getContentIfNotHandled()?.let{
                startActivity(Intent(this , ProfileActivity::class.java))
                finish()
           }
        })
    }
}