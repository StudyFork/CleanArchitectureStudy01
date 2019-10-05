package dev.daeyeon.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import dev.daeyeon.data.local.entity.RepoEntity
import io.reactivex.Maybe

@Dao
abstract class RepoDao: BaseDao<RepoEntity> {
    @Query("SELECT * FROM REPOS_TB WHERE OWNER_NM = (:userName)")
    abstract fun getRepos(userName: String): Maybe<List<RepoEntity>>
}