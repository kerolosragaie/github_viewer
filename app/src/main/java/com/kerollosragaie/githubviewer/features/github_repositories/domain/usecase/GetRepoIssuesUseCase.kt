package com.kerollosragaie.githubviewer.features.github_repositories.domain.usecase

import androidx.paging.PagingData
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseIssueItemModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.repo.GitHubRepo
import com.kerollosragaie.githubviewer.core.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetRepoIssuesUseCase(private val gitHubRepo: GitHubRepo) {
    suspend operator fun invoke(
        ownerName: String,
        repoName: String
    ): ResultState<Flow<PagingData<ResponseIssueItemModel>>> =
        withContext(Dispatchers.IO) {
            gitHubRepo.getRepoIssues(ownerName, repoName)
        }
}