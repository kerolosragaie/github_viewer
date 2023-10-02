package com.kerollosragaie.githubviewer.features.github_repositories.data.mappers

import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.OwnerModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseRepositoriesItemModel
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.OwnerDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseRepositoriesItemDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.entity.OwnerEntity
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.entity.ResponseRepositoriesItemEntity

fun OwnerDto.fromDtoToModel(): OwnerModel = OwnerModel(
    avatarUrl, id, nodeId, type, url, login,
)

fun OwnerEntity.fromEntityToModel(): OwnerModel = OwnerModel(
    id = id, avatarUrl = avatarUrl, type = type, nodeId = nodeId, url = url, login = login
)

fun OwnerModel.fromModelToEntity(): OwnerEntity = OwnerEntity(
    id = id,
    avatarUrl = avatarUrl,
    type = type,
    nodeId = nodeId,
    url = url,
    login = login,
)

fun OwnerDto.fromDtoToEntity(): OwnerEntity = OwnerEntity(
    id = id,
    avatarUrl = avatarUrl,
    type = type,
    nodeId = nodeId,
    url = url,
    login = login,
)


fun ResponseRepositoriesItemDto.fromDtoToModel(): ResponseRepositoriesItemModel =
    ResponseRepositoriesItemModel(
        id = id,
        name = name,
        ownerModel = ownerDto?.fromDtoToModel(),
        description = description,
        nodeId = nodeId,
        fullName = fullName,
        privateRepo = privateRepo,
        url = url,
    )

fun ResponseRepositoriesItemEntity.toResponseRepositoriesItemModel(): ResponseRepositoriesItemModel =
    ResponseRepositoriesItemModel(
        id = id,
        name = name,
        ownerModel = ownerEntity?.fromEntityToModel(),
        description = description,
        nodeId = nodeId,
        fullName = fullName,
        privateRepo = privateRepo,
        url = url,
    )

fun ResponseRepositoriesItemDto.fromDtoToEntity(): ResponseRepositoriesItemEntity =
    ResponseRepositoriesItemEntity(
        id = id,
        name = name,
        ownerEntity = ownerDto?.fromDtoToEntity(),
        description = description,
        nodeId = nodeId,
        fullName = fullName,
        privateRepo = privateRepo,
        url = url,
    )
