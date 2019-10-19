package com.beok.reposearch.data.model

import com.google.gson.annotations.SerializedName

data class Owner(

    @SerializedName("gists_url")
    val gistsUrl: String? = null,

    @SerializedName("repos_url")
    val reposUrl: String? = null,

    @SerializedName("following_url")
    val followingUrl: String? = null,

    @SerializedName("starred_url")
    val starredUrl: String? = null,

    @SerializedName("login")
    val login: String? = null,

    @SerializedName("followers_url")
    val followersUrl: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String? = null,

    @SerializedName("received_events_url")
    val receivedEventsUrl: String? = null,

    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @SerializedName("events_url")
    val eventsUrl: String? = null,

    @SerializedName("html_url")
    val htmlUrl: String? = null,

    @SerializedName("site_admin")
    val siteAdmin: Boolean? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("gravatar_id")
    val gravatarId: String? = null,

    @SerializedName("node_id")
    val nodeId: String? = null,

    @SerializedName("organizations_url")
    val organizationsUrl: String? = null
)