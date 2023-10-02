package com.kerollosragaie.githubviewer.github_repositories.data

import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.OwnerDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ReactionsDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseIssueItemDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseRepoDetailsDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseRepositoriesItemDto


val fakeOwnerDto = OwnerDto(
    avatarUrl = "https://github.com/avatar.png",
    id = 5678,
    nodeId = "123456789",
    type = "User",
    url = "https://github.com/githubuser",
    login = "user"
)

val fakeRepositoryItem = ResponseRepositoriesItemDto(
    description = "This is a sample repository",
    fullName = "githubuser/sample-repo",
    id = 1234,
    name = "sample-repo",
    nodeId = "123456789",
    ownerDto = fakeOwnerDto,
    privateRepo = false,
    url = "https://github.com/githubuser/sample-repo"
)

val fakeRepoDetails = ResponseRepoDetailsDto(
    createdAt = "2021-01-01",
    description = "This is a sample repository",
    forksCount = 10,
    fullName = "githubuser/sample-repo",
    homepage = "https://github.com/githubuser/sample-repo",
    id = 1234,
    name = "sample-repo",
    networkCount = 5,
    nodeId = "123456789",
    ownerDto = fakeOwnerDto,
    privateRepo = false,
    pushedAt = "2021-02-01",
    size = 1024,
    sshUrl = "git@github.com:githubuser/sample-repo.git",
    stargazersCount = 50,
    subscribersCount = 20,
    updatedAt = "2021-03-01",
    url = "https://github.com/githubuser/sample-repo",
    visibility = "public",
    watchersCount = 30
)

val fakeReactionsDto = ReactionsDto(
    totalCount = 10,
    url = "https://github.com/reactions"
)

val fakeIssueItem = ResponseIssueItemDto(
    createdAt = "2021-01-01",
    id = 1234,
    nodeId = "123456789",
    reactions = fakeReactionsDto,
    repositoryUrl = "https://github.com/githubuser/sample-repo",
    state = "open",
    title = "Sample Issue",
    updatedAt = "2021-02-01",
    url = "https://github.com/githubuser/sample-repo/issues/1",
    ownerDto = fakeOwnerDto
)

