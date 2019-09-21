package dev.daeyeon.data.repository

import dev.daeyeon.data.datasource.LocalRepoDataSource
import dev.daeyeon.data.datasource.RemoteRepoDataSource
import dev.daeyeon.domain.entity.Repo
import dev.daeyeon.domain.repository.RepoRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableSource

class RepoRepositoryImpl(
    private val local: LocalRepoDataSource,
    private val remote: RemoteRepoDataSource
) : RepoRepository {
    override fun getRepos(
        userName: String,
        isForceUpdate: Boolean
    ): Observable<List<Repo>> {
        return Observable.concatDelayError(
            mutableListOf<ObservableSource<List<Repo>>>().apply {
                add(local.getRepos(userName).toObservable())
                if (isForceUpdate) {
                    add(remote.getRepos(userName).toObservable())
                }
            }
        )
    }

    override fun saveRepos(repos: List<Repo>): Completable =
            local.insertAllRepo(repos)
}