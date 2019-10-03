package com.example.cleanarchitecturestudy.di.module

import com.example.cleanarchitecturestudy.di.annotation.ApplicationScope
import com.example.data.GithubRepositoryImpl
import com.example.data.remote.GithubApi
import com.example.domains.repositories.GithubRepository
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @ApplicationScope
    @Provides
    fun provideGithubRepository(githubApi: GithubApi): GithubRepository =
        GithubRepositoryImpl(githubApi)
}