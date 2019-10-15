package com.beok.reposearch.data.model

import com.beok.reposearch.domain.entity.LicenseEntity
import com.beok.reposearch.domain.entity.ReposEntity
import com.google.gson.annotations.SerializedName

data class Repos(

    @SerializedName("stargazers_count")
    val stargazersCount: Int? = null,

    @SerializedName("pushed_at")
    val pushedAt: String? = null,

    @SerializedName("subscription_url")
    val subscriptionUrl: String? = null,

    @SerializedName("language")
    val language: String? = null,

    @SerializedName("branches_url")
    val branchesUrl: String? = null,

    @SerializedName("issue_comment_url")
    val issueCommentUrl: String? = null,

    @SerializedName("labels_url")
    val labelsUrl: String? = null,

    @SerializedName("subscribers_url")
    val subscribersUrl: String? = null,

    @SerializedName("releases_url")
    val releasesUrl: String? = null,

    @SerializedName("svn_url")
    val svnUrl: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("forks")
    val forks: Int? = null,

    @SerializedName("archive_url")
    val archiveUrl: String? = null,

    @SerializedName("git_refs_url")
    val gitRefsUrl: String? = null,

    @SerializedName("forks_url")
    val forksUrl: String? = null,

    @SerializedName("statuses_url")
    val statusesUrl: String? = null,

    @SerializedName("ssh_url")
    val sshUrl: String? = null,

    @SerializedName("license")
    val license: License? = null,

    @SerializedName("full_name")
    val fullName: String? = null,

    @SerializedName("size")
    val size: Int? = null,

    @SerializedName("languages_url")
    val languagesUrl: String? = null,

    @SerializedName("html_url")
    val htmlUrl: String? = null,

    @SerializedName("collaborators_url")
    val collaboratorsUrl: String? = null,

    @SerializedName("clone_url")
    val cloneUrl: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("pulls_url")
    val pullsUrl: String? = null,

    @SerializedName("default_branch")
    val defaultBranch: String? = null,

    @SerializedName("hooks_url")
    val hooksUrl: String? = null,

    @SerializedName("trees_url")
    val treesUrl: String? = null,

    @SerializedName("tags_url")
    val tagsUrl: String? = null,

    @SerializedName("private")
    val jsonMemberPrivate: Boolean? = null,

    @SerializedName("contributors_url")
    val contributorsUrl: String? = null,

    @SerializedName("has_downloads")
    val hasDownloads: Boolean? = null,

    @SerializedName("notifications_url")
    val notificationsUrl: String? = null,

    @SerializedName("open_issues_count")
    val openIssuesCount: Int? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("watchers")
    val watchers: Int? = null,

    @SerializedName("keys_url")
    val keysUrl: String? = null,

    @SerializedName("deployments_url")
    val deploymentsUrl: String? = null,

    @SerializedName("has_projects")
    val hasProjects: Boolean? = null,

    @SerializedName("archived")
    val archived: Boolean? = null,

    @SerializedName("has_wiki")
    val hasWiki: Boolean? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("comments_url")
    val commentsUrl: String? = null,

    @SerializedName("stargazers_url")
    val stargazersUrl: String? = null,

    @SerializedName("disabled")
    val disabled: Boolean? = null,

    @SerializedName("git_url")
    val gitUrl: String? = null,

    @SerializedName("has_pages")
    val hasPages: Boolean? = null,

    @SerializedName("owner")
    val owner: Owner? = null,

    @SerializedName("commits_url")
    val commitsUrl: String? = null,

    @SerializedName("compare_url")
    val compareUrl: String? = null,

    @SerializedName("git_commits_url")
    val gitCommitsUrl: String? = null,

    @SerializedName("blobs_url")
    val blobsUrl: String? = null,

    @SerializedName("git_tags_url")
    val gitTagsUrl: String? = null,

    @SerializedName("merges_url")
    val mergesUrl: String? = null,

    @SerializedName("downloads_url")
    val downloadsUrl: String? = null,

    @SerializedName("has_issues")
    val hasIssues: Boolean? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("contents_url")
    val contentsUrl: String? = null,

    @SerializedName("mirror_url")
    val mirrorUrl: Any? = null,

    @SerializedName("milestones_url")
    val milestonesUrl: String? = null,

    @SerializedName("teams_url")
    val teamsUrl: String? = null,

    @SerializedName("fork")
    val fork: Boolean? = null,

    @SerializedName("issues_url")
    val issuesUrl: String? = null,

    @SerializedName("events_url")
    val eventsUrl: String? = null,

    @SerializedName("issue_events_url")
    val issueEventsUrl: String? = null,

    @SerializedName("assignees_url")
    val assigneesUrl: String? = null,

    @SerializedName("open_issues")
    val openIssues: Int? = null,

    @SerializedName("watchers_count")
    val watchersCount: Int? = null,

    @SerializedName("node_id")
    val nodeId: String? = null,

    @SerializedName("homepage")
    val homepage: String? = null,

    @SerializedName("forks_count")
    val forksCount: Int? = null
)

fun Repos.mappingToDomain(): ReposEntity =
    ReposEntity(
        name = name ?: "",
        fork = fork ?: false,
        htmlUrl = htmlUrl ?: "",
        language = language ?: "",
        stargazersCount = stargazersCount ?: 0,
        forks = forks ?: 0,
        license = license?.mappingToDomain() ?: LicenseEntity(""),
        updateAt = updatedAt ?: ""
    )
