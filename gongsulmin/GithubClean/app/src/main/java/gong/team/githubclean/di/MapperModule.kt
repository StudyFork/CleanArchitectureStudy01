package gong.team.githubclean.di

import gong.team.data.mapper.GithubOwnerMapper
import gong.team.data.mapper.GithubSearchItemMapper
import org.koin.dsl.module

val mapperModule = module {
    single { GithubSearchItemMapper(get()) }
    single { GithubOwnerMapper() }
}