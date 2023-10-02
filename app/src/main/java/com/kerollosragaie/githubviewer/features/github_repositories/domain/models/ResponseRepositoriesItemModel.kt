package com.kerollosragaie.githubviewer.features.github_repositories.domain.models


data class ResponseRepositoriesItemModel(
    val id: Int?,
    val name: String?,
    val ownerModel: OwnerModel?,
    val description: String?,
    val nodeId: String?,
    val fullName: String?,
    val privateRepo: Boolean?,
    val url: String?,
)

data class OwnerModel(
    val avatarUrl: String?,
    val id: Int?,
    val nodeId: String?,
    val type: String?,
    val url: String?,
    val login: String?
)

