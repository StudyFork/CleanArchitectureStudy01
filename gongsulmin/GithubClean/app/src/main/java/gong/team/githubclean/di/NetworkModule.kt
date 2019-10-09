package gong.team.githubclean.di

import gong.team.data.GithubApi
import gong.team.data.GithubApiFactory
import gong.team.data.GithubLoginApi
import org.koin.dsl.module

val networkModule = module {
    single <GithubApi>{
        GithubApiFactory.providerGithubApi(GithubApi.BASE_URL)
    }

    single <GithubLoginApi>{
        GithubApiFactory.providerGithubApi(GithubLoginApi.BASE_URL)
    }

}