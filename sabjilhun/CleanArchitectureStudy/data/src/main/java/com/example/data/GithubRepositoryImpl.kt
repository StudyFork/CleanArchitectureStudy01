package com.example.data

import android.util.Log
import com.example.data.remote.GithubApi
import com.example.domains.repositories.GithubRepository

class GithubRepositoryImpl constructor(
    private val githubApi: GithubApi
) : GithubRepository {

    override fun getRepositories(query: String) {
        Log.i("테스트", "$query 레포지토리 검색")
    }
}