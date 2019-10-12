package gong.team.data.datasource.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gong.team.data.datasource.local.entity.Token
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToken(token: Token): Completable

    @Query("Select * from Token")
    fun selectToken(): Single<List<Token>>
}