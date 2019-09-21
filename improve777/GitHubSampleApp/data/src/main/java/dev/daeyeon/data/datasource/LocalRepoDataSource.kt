package dev.daeyeon.data.datasource

import dev.daeyeon.domain.entity.Repo
import io.reactivex.Completable
import io.reactivex.Maybe

interface LocalRepoDataSource {

    fun insertRepo(repo: Repo): Completable

    fun insertAllRepo(repos: List<Repo>): Completable

    fun getRepos(userName: String): Maybe<List<Repo>>
}