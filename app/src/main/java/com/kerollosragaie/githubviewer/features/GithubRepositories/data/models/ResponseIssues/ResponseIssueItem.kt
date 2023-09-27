package com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseIssues

import com.google.gson.annotations.SerializedName
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepositories.Owner
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepositories.toOwnerModel

data class ResponseIssueItem(
    @SerializedName("created_at") val createdAt: String?,
    val id: Int?,
    @SerializedName("node_id") val nodeId: String?,
    val reactions: Reactions?,
    @SerializedName("repository_url") val repositoryUrl: String?,
    val state: String?,
    val title: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    val url: String?,
    val owner: Owner?
)

fun ResponseIssueItem.toResponseIssueItemModel(): ResponseIssueItemModel = ResponseIssueItemModel(
    createdAt,
    id,
    nodeId,
    reactions?.toReactionsModel(),
    repositoryUrl,
    state,
    title,
    updatedAt,
    url,
    owner?.toOwnerModel(),
)