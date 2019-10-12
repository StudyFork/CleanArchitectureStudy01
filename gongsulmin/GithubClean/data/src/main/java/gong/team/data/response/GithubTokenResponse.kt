package gong.team.data.response


import com.google.gson.annotations.SerializedName

data class GithubTokenResponse(
    @SerializedName("app")
    val app: App,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("fingerprint")
    val fingerprint: Any,
    @SerializedName("hashed_token")
    val hashedToken: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("note")
    val note: String,
    @SerializedName("note_url")
    val noteUrl: Any,
    @SerializedName("scopes")
    val scopes: List<String>,
    @SerializedName("token")
    val token: String,
    @SerializedName("token_last_eight")
    val tokenLastEight: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("url")
    val url: String
) {
    data class App(
        @SerializedName("client_id")
        val clientId: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )
}