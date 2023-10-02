package com.kerollosragaie.githubviewer.features.github_repositories.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.entity.ResponseRepositoriesItemEntity

@Dao
interface RepositoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<ResponseRepositoriesItemEntity>)

    @Query("SELECT * FROM responserepositoriesitementity")
    fun getAllRepos(): PagingSource<Int, ResponseRepositoriesItemEntity>

    @Query("SELECT COUNT(*) FROM responserepositoriesitementity")
    suspend fun getReposCount(): Int

    @Query("DELETE FROM responserepositoriesitementity")
    suspend fun clearAll()
}