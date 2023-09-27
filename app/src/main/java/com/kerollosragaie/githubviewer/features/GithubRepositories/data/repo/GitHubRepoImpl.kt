package com.kerollosragaie.githubviewer.features.GithubRepositories.data.repo

import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseIssues.ResponseIssues
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseIssues.ResponseIssuesModel
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseIssues.toResponseIssueItemModel
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepoDetails.ResponseRepoDetailsModel
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepoDetails.toResponseRepoDetailsModel
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepositories.ResponseRepositoriesModel
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepositories.toResponseRepositoriesItemModel
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.remote.RemoteServices
import com.kerollosragaie.githubviewer.features.GithubRepositories.domain.repo.GitHubRepo

class GitHubRepoImpl(private val remoteServices: RemoteServices) : GitHubRepo {
    override suspend fun getRepositories(): ResponseRepositoriesModel {
        return remoteServices.getRepositories()
            .map { it.toResponseRepositoriesItemModel() } as ResponseRepositoriesModel
    }

    override suspend fun getRepoDetails(
        ownerName: String,
        repoName: String
    ): ResponseRepoDetailsModel {
        return remoteServices.getRepoDetails(ownerName, repoName).toResponseRepoDetailsModel()
    }

    override suspend fun getRepoIssues(ownerName: String, repoName: String): ResponseIssuesModel {
        val responseIssues: ResponseIssues = remoteServices.getRepoIssues(ownerName, repoName)
        return responseIssues.map { it.toResponseIssueItemModel() } as ResponseIssuesModel
    }
}