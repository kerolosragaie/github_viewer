package com.kerollosragaie.githubviewer.features.github_repositories.data.local.data_source

import androidx.paging.PagingData
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.entity.ResponseRepositoriesItemEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getRepositories(): Flow<PagingData<ResponseRepositoriesItemEntity>>

    suspend fun insertAll(repos: List<ResponseRepositoriesItemEntity>)
    suspend fun getReposCount(): Int

    suspend fun clearAll()

}

