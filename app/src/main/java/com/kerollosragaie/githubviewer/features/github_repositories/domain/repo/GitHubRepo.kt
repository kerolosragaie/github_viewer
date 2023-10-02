package com.kerollosragaie.githubviewer.features.github_repositories.domain.repo

import androidx.paging.PagingData
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseIssueItemModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseRepoDetailsModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseRepositoriesItemModel
import com.kerollosragaie.githubviewer.core.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface GitHubRepo {
    suspend fun getRepositories(): ResultState<Flow<PagingData<ResponseRepositoriesItemModel>>>
    suspend fun getRepoDetails(
        ownerName: String,
        repoName: String
    ): ResultState<ResponseRepoDetailsModel>

    suspend fun getRepoIssues(
        ownerName: String,
        repoName: String
    ): ResultState<Flow<PagingData<ResponseIssueItemModel>>>
}