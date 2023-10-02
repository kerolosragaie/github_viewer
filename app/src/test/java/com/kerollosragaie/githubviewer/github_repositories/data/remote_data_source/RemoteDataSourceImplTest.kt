package com.kerollosragaie.githubviewer.github_repositories.data.remote_data_source


import androidx.paging.PagingData
import com.kerollosragaie.githubviewer.github_repositories.data.fakeIssueItem
import com.kerollosragaie.githubviewer.github_repositories.data.fakeRepoDetails
import com.kerollosragaie.githubviewer.github_repositories.data.fakeRepositoryItem
import com.kerollosragaie.githubviewer.features.github_repositories.data.remote.api.RemoteServices
import com.kerollosragaie.githubviewer.features.github_repositories.data.remote.data_source.RemoteDataSourceImpl
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class RemoteDataSourceImplTest {

    @Mock
    private lateinit var remoteServices: RemoteServices

    private lateinit var remoteDataSource: RemoteDataSourceImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = RemoteDataSourceImpl(remoteServices)
    }

    @Test
    fun testGetRepositories() {
        val repositoriesList = listOf(fakeRepositoryItem, fakeRepositoryItem)

        runBlocking {
            `when`(remoteServices.getRepositories()).thenReturn(repositoriesList)

            val result = remoteDataSource.getRepositories()

            assertEquals(repositoriesList, result)
        }
    }

    @Test
    fun testGetRepoDetails() {
        val ownerName = "owner"
        val repoName = "repo"
        val repoDetails = fakeRepoDetails
        runBlocking {
            `when`(remoteServices.getRepoDetails(ownerName, repoName)).thenReturn(repoDetails)
            val result = remoteDataSource.getRepoDetails(ownerName, repoName)

            assertEquals(repoDetails, result)
        }
    }

    @Test
    fun testGetRepoIssues() {
        val ownerName = "owner"
        val repoName = "repo"

        val issuesList = listOf(fakeIssueItem, fakeIssueItem)
        val expectedResponse = PagingData.from(issuesList)

        runBlocking {
            `when`(remoteServices.getRepoIssues(ownerName, repoName, 1, 10)).thenReturn(issuesList)

            remoteDataSource.getRepoIssues(ownerName, repoName).catch {
                assertEquals(expectedResponse, it)
            }
        }
    }
}

