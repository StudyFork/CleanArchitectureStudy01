package dev.daeyeon.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.daeyeon.data.local.dao.RepoDao
import dev.daeyeon.data.local.entity.RepoEntity

@Database(entities = [RepoEntity::class], version = 1)
abstract class LocalDataBase : RoomDatabase() {

    abstract fun repoDao(): RepoDao
}