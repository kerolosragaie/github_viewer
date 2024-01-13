package com.kerollosragaie.githubviewer.features.github_repositories.domain.usecase

import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseRepoDetailsModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.repo.GitHubRepo
import com.kerollosragaie.githubviewer.core.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRepoDetailsUseCase(private val gitHubRepo: GitHubRepo) {
    suspend operator fun invoke(ownerName: String, repoName: String)
            : ResultState<ResponseRepoDetailsModel> =
        withContext(Dispatchers.IO) {
            gitHubRepo.getRepoDetails(ownerName, repoName)
        }
}