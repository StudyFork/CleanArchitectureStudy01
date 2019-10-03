package com.example.domains.usecases

import com.example.domains.repositories.GithubRepository
import javax.inject.Inject

class SearchRepositoriesUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    fun getRepositories() {
        githubRepository.getRepositories("test")
    }
}