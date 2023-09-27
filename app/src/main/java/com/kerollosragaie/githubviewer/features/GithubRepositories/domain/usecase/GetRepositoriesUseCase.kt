package com.kerollosragaie.githubviewer.features.GithubRepositories.domain.usecase

import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepositories.ResponseRepositoriesModel
import com.kerollosragaie.githubviewer.features.GithubRepositories.domain.repo.GitHubRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRepositoriesUseCase(private val gitHubRepo: GitHubRepo) {
    suspend operator fun invoke(): ResponseRepositoriesModel {
        return withContext(Dispatchers.IO) {
            gitHubRepo.getRepositories()
        }
    }
}