package com.kerollosragaie.githubviewer.features.GithubRepositories.domain.usecase

import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepoDetails.ResponseRepoDetailsModel
import com.kerollosragaie.githubviewer.features.GithubRepositories.domain.repo.GitHubRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRepoDetailsUseCase(private val gitHubRepo: GitHubRepo) {
    suspend operator fun invoke(ownerName: String, repoName: String)
            : ResponseRepoDetailsModel {
        return withContext(Dispatchers.IO) {
            gitHubRepo.getRepoDetails(ownerName, repoName)
        }
    }
}