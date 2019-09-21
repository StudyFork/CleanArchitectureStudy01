package dev.daeyeon.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.daeyeon.domain.entity.Repo

@Entity(tableName = "REPOS_TB")
data class RepoEntity(
    @PrimaryKey @ColumnInfo(name = "REPO_ID") val id: Long,
    @ColumnInfo(name = "REPO_NM") val repoName: String,
    @ColumnInfo(name = "OWNER_NM") val ownerName: String,
    @ColumnInfo(name = "REPO_URL") val repoUrl: String,
    @ColumnInfo(name = "STAR_CNT") val starCount: Int,
    @ColumnInfo(name = "WATCHERS_CNT") val watchersCount: Int,
    @ColumnInfo(name = "LANG") val language: String?,
    @ColumnInfo(name = "LICENSE_NM") val licenseName: String?,
    @ColumnInfo(name = "FORKS_CNT") val forksCount: Int
)