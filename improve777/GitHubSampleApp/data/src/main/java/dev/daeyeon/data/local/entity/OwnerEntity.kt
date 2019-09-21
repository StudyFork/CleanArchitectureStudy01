package dev.daeyeon.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OWNER_TB")
data class OwnerEntity(
    @PrimaryKey @ColumnInfo(name = "OWNER_ID") val id: Long,
    @ColumnInfo(name = "OWNER_NM") val name: String,
    @ColumnInfo(name = "AVATAR_URL") val avatarUrl: String,
    @ColumnInfo(name = "OWNER_URL") val ownerUrl: String
)