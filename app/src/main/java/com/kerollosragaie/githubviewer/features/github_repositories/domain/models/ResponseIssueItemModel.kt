package com.kerollosragaie.githubviewer.features.github_repositories.domain.models

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

data class ReactionsModel(
    val totalCount: Int?,
    val url: String?
)

