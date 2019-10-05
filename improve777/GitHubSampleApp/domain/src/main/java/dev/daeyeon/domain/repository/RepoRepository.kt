package dev.daeyeon.domain.repository

import dev.daeyeon.domain.entity.Repo
import io.reactivex.Completable
import io.reactivex.Observable

interface RepoRepository {

    fun getRepos(
        userName: String,
        isForceUpdate: Boolean
    ): Observable<List<Repo>>

    fun saveRepos(
        repos: List<Repo>
    ): Completable
}