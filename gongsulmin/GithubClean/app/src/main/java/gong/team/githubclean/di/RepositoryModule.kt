package gong.team.githubclean.di

import gong.team.data.datasource.local.GithubUserLocalDataSource
import gong.team.data.datasource.local.GithubUserLocalDataSourceImpl
import gong.team.data.datasource.remote.GithubSearchRemoteDataSource
import gong.team.data.datasource.remote.GithubSearchRemoteDatasourceImpl
import gong.team.data.datasource.remote.GithubUserInfoRemoteDataSource
import gong.team.data.datasource.remote.GithubUserInfoRemoteDataSourceImpl
import gong.team.data.repository.GithubRepositoryImpl
import gong.team.domain.repository.GithubRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<GithubSearchRemoteDataSource> { GithubSearchRemoteDatasourceImpl(get() , get()) }
    single<GithubUserLocalDataSource> {GithubUserLocalDataSourceImpl(get())}
    single<GithubUserInfoRemoteDataSource> { GithubUserInfoRemoteDataSourceImpl(get()) }
    single<GithubRepository> { GithubRepositoryImpl(get() , get() , get() , get()) }
}