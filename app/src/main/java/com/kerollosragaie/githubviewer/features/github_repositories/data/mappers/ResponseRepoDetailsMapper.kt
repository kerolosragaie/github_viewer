package com.kerollosragaie.githubviewer.features.github_repositories.data.mappers

import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseRepoDetailsDto
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseRepoDetailsModel

fun ResponseRepoDetailsDto.fromDtoToModel(): ResponseRepoDetailsModel =
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
        ownerDto?.fromDtoToModel(),
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