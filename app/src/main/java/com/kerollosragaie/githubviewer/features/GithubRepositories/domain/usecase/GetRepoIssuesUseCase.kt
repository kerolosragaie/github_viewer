package com.kerollosragaie.githubviewer.features.GithubRepositories.domain.usecase

import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseIssues.ResponseIssuesModel
import com.kerollosragaie.githubviewer.features.GithubRepositories.domain.repo.GitHubRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRepoIssuesUseCase(private val gitHubRepo: GitHubRepo) {
    suspend operator fun invoke(ownerName: String, repoName: String): ResponseIssuesModel {
        return withContext(Dispatchers.IO) {
            gitHubRepo.getRepoIssues(ownerName, repoName)
        }
    }
}