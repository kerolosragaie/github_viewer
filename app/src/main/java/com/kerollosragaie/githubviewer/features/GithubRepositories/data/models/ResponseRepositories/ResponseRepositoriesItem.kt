package com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepositories

import com.google.gson.annotations.SerializedName

data class ResponseRepositoriesItem(
    @SerializedName("description") val description: String?,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("node_id") val nodeId: String?,
    @SerializedName("owner") val owner: Owner?,
    @SerializedName("private") val privateRepo: Boolean?,
    @SerializedName("url") val url: String?
)

fun ResponseRepositoriesItem.toResponseRepositoriesItemModel(): ResponseRepositoriesItemModel =
    ResponseRepositoriesItemModel(
        id, name, owner, description
    )