package gong.team.data.request


import com.google.gson.annotations.SerializedName

data class GithubTokenRequest(
    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("client_secret")
    val clientSecret: String,
    @SerializedName("note")
    val note: String,
    @SerializedName("scopes")
    val scopes: List<String>
)