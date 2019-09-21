package gong.team.githubclean.di

import gong.team.data.GithubApi
import gong.team.data.GithubApiFactory
import org.koin.dsl.module

val networkModule = module {
    single {
        GithubApiFactory.providerGithubApi(GithubApi.BASE_URL)
    }
}