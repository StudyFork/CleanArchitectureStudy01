package gong.team.data.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gong.team.data.datasource.local.entity.Token

@Database(entities = [Token::class] , version = 1)
abstract class LocalDataBase: RoomDatabase() {
    abstract fun TokenDao(): TokenDao

    companion object {
        private var instance: LocalDataBase? = null
        fun getInstance(context: Context): LocalDataBase? {
            if (instance == null) {

                synchronized(LocalDataBase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext ,
                        LocalDataBase::class.java ,
                        "local_db.db"
                    ).build()
                }
            }
            return instance
        }
    }
}