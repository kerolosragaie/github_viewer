package com.kerollosragaie.githubviewer.github_repositories.domain.usecase

import androidx.paging.PagingData
import com.kerollosragaie.githubviewer.core.utils.ResultState
import com.kerollosragaie.githubviewer.github_repositories.data.fakeIssueItem
import com.kerollosragaie.githubviewer.features.github_repositories.data.mappers.fromDtoToModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.repo.GitHubRepo
import com.kerollosragaie.githubviewer.features.github_repositories.domain.usecase.GetRepoIssuesUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetRepoIssuesUseCaseTest {

    @Mock
    private lateinit var gitHubRepo: GitHubRepo

    private lateinit var getRepoIssuesUseCase: GetRepoIssuesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getRepoIssuesUseCase = GetRepoIssuesUseCase(gitHubRepo)
    }

    @Test
    fun `invoke should return repo issues result success`() {
        val ownerName = "owner"
        val repoName = "repo"
        val expectedResponse = flowOf(PagingData.from(listOf(
            fakeIssueItem.fromDtoToModel(),
            fakeIssueItem.fromDtoToModel())))

        runBlocking {
            `when`(gitHubRepo.getRepoIssues(ownerName, repoName)).thenReturn(ResultState.Success(expectedResponse))

            val actualResponse = getRepoIssuesUseCase(ownerName, repoName)

            assertEquals(expectedResponse, actualResponse.data)
        }
    }


    @Test
    fun `invoke should return repo issues result failure`() {
        val ownerName = "owner"
        val repoName = "repo"

        runBlocking {
            `when`(gitHubRepo.getRepoIssues(ownerName, repoName)).thenReturn(ResultState.Failure("No network"))

            val actualResponse = getRepoIssuesUseCase(ownerName, repoName)

            assertEquals("No network", actualResponse.message)
        }
    }
}