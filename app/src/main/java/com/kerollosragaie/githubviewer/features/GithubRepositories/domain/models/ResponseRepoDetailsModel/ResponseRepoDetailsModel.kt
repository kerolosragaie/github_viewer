package com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepoDetails

import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepositories.Owner


data class ResponseRepoDetailsModel(
    val createdAt: String?,
    val description: String?,
    val forksCount: Int?,
    val fullName: String?,
    val homepage: String?,
    val id: Int?,
    val name: String?,
    val networkCount: Int?,
    val nodeId: String?,
    val owner: Owner?,
    val privateRepo: Boolean?,
    val pushedAt: String?,
    val size: Int?,
    val sshUrl: String?,
    val stargazersCount: Int?,
    val subscribersCount: Int?,
    val updatedAt: String?,
    val url: String?,
    val visibility: String?,
    val watchersCount: Int?,
)

