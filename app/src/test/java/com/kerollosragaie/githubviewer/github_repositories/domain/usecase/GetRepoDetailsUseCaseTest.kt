package com.kerollosragaie.githubviewer.github_repositories.domain.usecase

import com.kerollosragaie.githubviewer.core.utils.ResultState
import com.kerollosragaie.githubviewer.github_repositories.data.fakeRepoDetails
import com.kerollosragaie.githubviewer.features.github_repositories.data.mappers.fromDtoToModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.repo.GitHubRepo
import com.kerollosragaie.githubviewer.features.github_repositories.domain.usecase.GetRepoDetailsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetRepoDetailsUseCaseTest {

    @Mock
    private lateinit var gitHubRepo: GitHubRepo

    private lateinit var getRepoDetailsUseCase: GetRepoDetailsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getRepoDetailsUseCase = GetRepoDetailsUseCase(gitHubRepo)
    }

    @Test
    fun `invoke should return repo details result success`() {
        val ownerName = "owner"
        val repoName = "repo"
        val expectedResponse = fakeRepoDetails.fromDtoToModel()
        runBlocking {
            `when`(gitHubRepo.getRepoDetails(ownerName, repoName)).thenReturn(ResultState.Success(expectedResponse))

            val actualResponse = getRepoDetailsUseCase(ownerName, repoName)

            assertEquals(expectedResponse, actualResponse.data)
        }
    }

    @Test
    fun `invoke should return repo details result failure`() {
        val ownerName = "owner"
        val repoName = "repo"

        runBlocking {
            `when`(gitHubRepo.getRepoDetails(ownerName, repoName)).thenReturn(ResultState.Failure("No network"))

            val actualResponse = getRepoDetailsUseCase(ownerName, repoName)

            assertEquals("No network", actualResponse.message)
        }
    }
}