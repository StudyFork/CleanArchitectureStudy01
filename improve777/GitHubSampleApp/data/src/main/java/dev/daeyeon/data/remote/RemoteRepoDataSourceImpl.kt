package dev.daeyeon.data.remote

import dev.daeyeon.data.datasource.RemoteRepoDataSource
import dev.daeyeon.data.mapper.RepoMapper
import dev.daeyeon.data.remote.api.GithubApi
import dev.daeyeon.data.scheduler.SchedulersProvider

class RemoteRepoDataSourceImpl(
    private val githubApi: GithubApi,
    private val scheduler: SchedulersProvider,
    private val repoMapper: RepoMapper
): RemoteRepoDataSource {

    override fun getRepos(userName: String)  =
        githubApi.getRepos(userName)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .map{ it.map(repoMapper::toRepo) }
}