package com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepositories

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("node_id") val nodeId: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("url") val url: String?
)

fun Owner.toOwnerModel(): OwnerModel = OwnerModel(
    avatarUrl, id, nodeId, type, url,
)