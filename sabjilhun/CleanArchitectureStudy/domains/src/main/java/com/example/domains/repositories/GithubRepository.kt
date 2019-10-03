package com.example.domains.repositories

interface GithubRepository {

    fun getRepositories(query: String)
}