package com.kerollosragaie.githubviewer.features.github_repositories.data.mappers

import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ReactionsModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseIssueItemModel
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ReactionsDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseIssueItemDto

fun ReactionsDto.fromDtoToModel(): ReactionsModel = ReactionsModel(
    totalCount, url,
)

fun ResponseIssueItemDto.fromDtoToModel(): ResponseIssueItemModel = ResponseIssueItemModel(
    createdAt,
    id,
    nodeId,
    reactions?.fromDtoToModel(),
    repositoryUrl,
    state,
    title,
    updatedAt,
    url,
    ownerDto?.fromDtoToModel(),
)