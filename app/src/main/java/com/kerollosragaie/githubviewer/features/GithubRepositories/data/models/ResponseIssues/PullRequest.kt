package com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseIssues

import com.google.gson.annotations.SerializedName

data class PullRequest(
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("merged_at") val mergedAt: Any?,
    @SerializedName("url") val url: String?
)

fun PullRequest.toPullRequestModel(): PullRequestModel = PullRequestModel(
    htmlUrl, mergedAt, url
)
