package com.kerollosragaie.githubviewer.features.github_repositories.data.repo

import androidx.paging.PagingData
import androidx.paging.map
import com.kerollosragaie.githubviewer.features.github_repositories.data.local.data_source.LocalDataSource
import com.kerollosragaie.githubviewer.features.github_repositories.data.mappers.fromDtoToEntity
import com.kerollosragaie.githubviewer.features.github_repositories.data.mappers.fromDtoToModel
import com.kerollosragaie.githubviewer.features.github_repositories.data.mappers.toResponseRepositoriesItemModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseIssueItemModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseRepoDetailsModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseRepositoriesItemModel
import com.kerollosragaie.githubviewer.features.github_repositories.data.remote.data_source.RemoteDataSource
import com.kerollosragaie.githubviewer.features.github_repositories.domain.repo.GitHubRepo
import com.kerollosragaie.githubviewer.core.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GitHubRepoImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : GitHubRepo {
    override suspend fun getRepositories(): ResultState<Flow<PagingData<ResponseRepositoriesItemModel>>> {
        if (localDataSource.getReposCount() == 0) {
            try {
                val remoteData = remoteDataSource.getRepositories().map { it.fromDtoToEntity() }
                localDataSource.insertAll(remoteData)
            } catch (e: Exception) {
                return ResultState.Failure(e.message)
            }
        }
        return ResultState.Success(
            localDataSource
                .getRepositories()
                .map { pagingData ->
                    pagingData.map {
                        it.toResponseRepositoriesItemModel()
                    }
                }
        )
    }

    override suspend fun getRepoDetails(
        ownerName: String,
        repoName: String
    ): ResultState<ResponseRepoDetailsModel> {
        return try {
            val result = remoteDataSource.getRepoDetails(ownerName, repoName).fromDtoToModel()
            ResultState.Success(result)
        } catch (e: Exception) {
            ResultState.Failure(e.message)
        }
    }

    override suspend fun getRepoIssues(
        ownerName: String,
        repoName: String
    ): ResultState<Flow<PagingData<ResponseIssueItemModel>>> {
        return try {
            val result = remoteDataSource.getRepoIssues(ownerName, repoName)
            val data: Flow<PagingData<ResponseIssueItemModel>> =
                result.map { pagingData ->
                    pagingData.map { issueDto ->
                        issueDto.fromDtoToModel()
                    }
                }
            ResultState.Success(data)
        } catch (e: Exception) {
            ResultState.Failure(e.message)
        }
    }

}