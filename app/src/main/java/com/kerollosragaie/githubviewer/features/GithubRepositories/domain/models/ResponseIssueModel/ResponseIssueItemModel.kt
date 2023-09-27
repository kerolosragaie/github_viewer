package com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseIssues

import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepositories.OwnerModel

data class ResponseIssueItemModel(
    val createdAt: String?,
    val id: Int?,
    val nodeId: String?,
    val reactionsModel: ReactionsModel?,
    val repositoryUrl: String?,
    val state: String?,
    val title: String?,
    val updatedAt: String?,
    val url: String?,
    val ownerModel: OwnerModel?
)

