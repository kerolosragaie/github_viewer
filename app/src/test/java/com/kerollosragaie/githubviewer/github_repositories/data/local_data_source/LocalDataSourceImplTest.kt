package com.kerollosragaie.githubviewer.github_repositories.data.local_data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kerollosragaie.githubviewer.github_repositories.data.fakeRepositoryItem
import com.kerollosragaie.githubviewer.features.github_repositories.data.local.dao.RepositoriesDao
import com.kerollosragaie.githubviewer.features.github_repositories.data.local.data_source.LocalDataSourceImpl
import com.kerollosragaie.githubviewer.features.github_repositories.data.mappers.fromDtoToEntity
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.entity.ResponseRepositoriesItemEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LocalDataSourceImplTest {
    @Mock
    private lateinit var repositoriesDao: RepositoriesDao

    private lateinit var localDataSource: LocalDataSourceImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        localDataSource = LocalDataSourceImpl(repositoriesDao)
    }


    @Test
    fun `test insertAll inserts repositories successfully`() {
        val mockRepos: List<ResponseRepositoriesItemEntity> = listOf(
            fakeRepositoryItem.fromDtoToEntity(),
            fakeRepositoryItem.fromDtoToEntity(),
        )
        val mockPagingSource = MockPagingSource(mockRepos)

        runBlocking {
            `when`(repositoriesDao.getAllRepos()).thenReturn(mockPagingSource)

            localDataSource.insertAll(mockRepos)

            // Verify that repositoriesDao.insertAll() is called with the correct data
            verify(repositoriesDao).insertAll(mockRepos)
        }
    }

    @Test
    fun `test getReposCount returns the correct count`() {
        val mockCount = 2
        runBlocking {
            `when`(repositoriesDao.getReposCount()).thenReturn(mockCount)

            val result = localDataSource.getReposCount()

            // Assert the result
            assertEquals(mockCount, result)
        }
    }

    @Test
    fun `test clearAll clears all repositories`() {
        runBlocking {
            localDataSource.clearAll()

            `when`(repositoriesDao.clearAll()).thenReturn(Unit)

            // Verify that repositoriesDao.clearAll() is called
            verify(repositoriesDao).clearAll()
        }
    }
}

class MockPagingSource(private val repos: List<ResponseRepositoriesItemEntity>) :
    PagingSource<Int, ResponseRepositoriesItemEntity>() {
    override fun getRefreshKey(state: PagingState<Int, ResponseRepositoriesItemEntity>): Int? {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseRepositoriesItemEntity> {
        return LoadResult.Page(
            data = repos,
            prevKey = null,
            nextKey = null
        )
    }
}