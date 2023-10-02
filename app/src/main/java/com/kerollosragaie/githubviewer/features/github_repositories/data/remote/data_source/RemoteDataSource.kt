package com.kerollosragaie.githubviewer.features.github_repositories.data.remote.data_source

import androidx.paging.PagingData
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseIssueItemDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseRepoDetailsDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseRepositoriesItemDto
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getRepositories(): List<ResponseRepositoriesItemDto>
    suspend fun getRepoDetails(
        ownerName: String,
        repoName: String
    ): ResponseRepoDetailsDto

    suspend fun getRepoIssues(ownerName: String, repoName: String): Flow<PagingData<ResponseIssueItemDto>>
}