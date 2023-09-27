package com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepoDetails

import com.google.gson.annotations.SerializedName
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepositories.Owner

data class ResponseRepoDetails(
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("forks_count") val forksCount: Int?,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("network_count") val networkCount: Int?,
    @SerializedName("node_id") val nodeId: String?,
    @SerializedName("owner") val owner: Owner?,
    @SerializedName("private") val privateRepo: Boolean?,
    @SerializedName("pushed_at") val pushedAt: String?,
    @SerializedName("size") val size: Int?,
    @SerializedName("ssh_url") val sshUrl: String?,
    @SerializedName("stargazers_count") val stargazersCount: Int?,
    @SerializedName("subscribers_count") val subscribersCount: Int?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("visibility") val visibility: String?,
    @SerializedName("watchers_count") val watchersCount: Int?,
)

fun ResponseRepoDetails.toResponseRepoDetailsModel(): ResponseRepoDetailsModel =
    ResponseRepoDetailsModel(
        createdAt,
        description,
        forksCount,
        fullName,
        homepage,
        id,
        name,
        networkCount,
        nodeId,
        owner,
        privateRepo,
        pushedAt,
        size,
        sshUrl,
        stargazersCount,
        subscribersCount,
        updatedAt,
        url,
        visibility,
        watchersCount
    )