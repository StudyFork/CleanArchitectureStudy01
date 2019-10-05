package dev.daeyeon.data.datasource

import dev.daeyeon.domain.entity.Repo
import io.reactivex.Maybe
import io.reactivex.Single

interface RemoteRepoDataSource {
    fun getRepos(userName: String): Single<List<Repo>>
}