package gong.team.githubclean.di

import gong.team.data.datasource.GithubSearchRemoteDataSource
import gong.team.data.datasource.GithubSearchRemoteDatasourceImpl
import gong.team.data.repository.GithubRepositoryImpl
import gong.team.domain.repository.GithubRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<GithubSearchRemoteDataSource> { GithubSearchRemoteDatasourceImpl(get()) }
    single<GithubRepository> { GithubRepositoryImpl(get() , get()) }
}