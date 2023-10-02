package com.kerollosragaie.githubviewer.features.github_repositories.data.remote.data_source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseIssueItemDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseRepoDetailsDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseRepositoriesItemDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.remote.api.RemoteServices
import com.kerollosragaie.githubviewer.features.github_repositories.data.remote.paging_source.IssuesPagingSource
import kotlinx.coroutines.flow.Flow


class RemoteDataSourceImpl(private val remoteServices: RemoteServices) : RemoteDataSource {
    override suspend fun getRepositories(): List<ResponseRepositoriesItemDto> =
        remoteServices.getRepositories()

    override suspend fun getRepoDetails(
        ownerName: String, repoName: String
    ): ResponseRepoDetailsDto = remoteServices.getRepoDetails(ownerName, repoName)

    override suspend fun getRepoIssues(
        ownerName: String, repoName: String
    ): Flow<PagingData<ResponseIssueItemDto>> {
        return Pager(
            PagingConfig(
                pageSize = 10,
            )
        ) {
            IssuesPagingSource(remoteServices, ownerName, repoName)
        }.flow
    }
}