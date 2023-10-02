package com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto

import com.google.gson.annotations.SerializedName

data class ResponseRepositoriesItemDto(
    @SerializedName("description") val description: String?,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("node_id") val nodeId: String?,
    @SerializedName("owner") val ownerDto: OwnerDto?,
    @SerializedName("private") val privateRepo: Boolean?,
    @SerializedName("url") val url: String?
)

data class OwnerDto(
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("node_id") val nodeId: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("login") val login: String?
)

