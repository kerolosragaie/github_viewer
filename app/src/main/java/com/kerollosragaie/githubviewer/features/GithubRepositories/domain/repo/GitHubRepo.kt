package com.kerollosragaie.githubviewer.features.GithubRepositories.domain.repo

import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseIssues.ResponseIssuesModel
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepoDetails.ResponseRepoDetailsModel
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepositories.ResponseRepositoriesModel

interface GitHubRepo {
    suspend fun getRepositories(): ResponseRepositoriesModel
    suspend fun getRepoDetails(ownerName: String, repoName:String): ResponseRepoDetailsModel
    suspend fun getRepoIssues(ownerName: String, repoName:String): ResponseIssuesModel
}