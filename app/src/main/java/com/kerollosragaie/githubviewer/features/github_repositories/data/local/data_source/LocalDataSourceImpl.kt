package com.kerollosragaie.githubviewer.features.github_repositories.data.local.data_source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kerollosragaie.githubviewer.features.github_repositories.data.local.dao.RepositoriesDao
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.entity.ResponseRepositoriesItemEntity
import kotlinx.coroutines.flow.Flow


class LocalDataSourceImpl(private val repositoriesDao: RepositoriesDao) : LocalDataSource {
    override suspend fun getRepositories(): Flow<PagingData<ResponseRepositoriesItemEntity>> =
        Pager(
            PagingConfig(
                pageSize = 10,
                prefetchDistance = 20,
            ),
        ) {
            repositoriesDao.getAllRepos()
        }.flow

    override suspend fun insertAll(repos: List<ResponseRepositoriesItemEntity>) =
        repositoriesDao.insertAll(repos)

    override suspend fun getReposCount() = repositoriesDao.getReposCount()
    override suspend fun clearAll() {
        repositoriesDao.clearAll()
    }
}
