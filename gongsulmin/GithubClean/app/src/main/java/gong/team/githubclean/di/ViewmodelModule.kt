package gong.team.githubclean.di

import gong.team.githubclean.ui.follow.FollowViewModel
import gong.team.githubclean.ui.login.LoginViewModel
import gong.team.githubclean.ui.main.MainViewModel
import gong.team.githubclean.ui.profile.ProfileVIewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ProfileVIewModel(get()) }
    viewModel { params -> FollowViewModel(get() , params[0] , params[1]) }
}