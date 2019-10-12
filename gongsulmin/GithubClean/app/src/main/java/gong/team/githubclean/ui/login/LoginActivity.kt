package gong.team.githubclean.ui.login

import android.os.Bundle
import gong.team.githubclean.R
import gong.team.githubclean.databinding.ActivityLoginBinding
import gong.team.githubclean.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            loginVM = loginViewModel
        }
    }
}