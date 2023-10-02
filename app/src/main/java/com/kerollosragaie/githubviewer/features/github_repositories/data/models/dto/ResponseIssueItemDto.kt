package com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto

import com.google.gson.annotations.SerializedName

data class ResponseIssueItemDto(
    @SerializedName("created_at") val createdAt: String?,
    val id: Int?,
    @SerializedName("node_id") val nodeId: String?,
    val reactions: ReactionsDto?,
    @SerializedName("repository_url") val repositoryUrl: String?,
    val state: String?,
    val title: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    val url: String?,
    val ownerDto: OwnerDto?
)

data class ReactionsDto(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("url") val url: String?
)
