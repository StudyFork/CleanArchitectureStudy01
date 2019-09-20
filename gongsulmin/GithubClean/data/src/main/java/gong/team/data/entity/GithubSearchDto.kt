package gong.team.data.entity


import com.google.gson.annotations.SerializedName

data class GithubSearchDto(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val githubSearchItemEntities: List<GithubSearchItemEntity>,
    @SerializedName("total_count")
    val totalCount: Int
)