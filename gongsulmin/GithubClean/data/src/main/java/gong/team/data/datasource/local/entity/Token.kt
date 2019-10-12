package gong.team.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
data class Token(

    @PrimaryKey
    val id: Int ,

    @ColumnInfo(name = "token")
    val token: String
)