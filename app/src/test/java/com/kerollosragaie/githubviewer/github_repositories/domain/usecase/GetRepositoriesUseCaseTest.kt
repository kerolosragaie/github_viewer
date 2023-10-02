package com.kerollosragaie.githubviewer.github_repositories.domain.usecase

import androidx.paging.PagingData
import com.kerollosragaie.githubviewer.core.utils.ResultState
import com.kerollosragaie.githubviewer.github_repositories.data.fakeRepositoryItem
import com.kerollosragaie.githubviewer.features.github_repositories.data.mappers.fromDtoToModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.repo.GitHubRepo
import com.kerollosragaie.githubviewer.features.github_repositories.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetRepositoriesUseCaseTest {

    @Mock
    private lateinit var gitHubRepo: GitHubRepo

    private lateinit var getRepositoriesUseCase: GetRepositoriesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getRepositoriesUseCase = GetRepositoriesUseCase(gitHubRepo)
    }

    @Test
    fun `invoke should return repositories result success`() {
        val expectedResponse = flowOf(
            PagingData.from(
                listOf(
                    fakeRepositoryItem.fromDtoToModel(),
                    fakeRepositoryItem.fromDtoToModel(),
                    fakeRepositoryItem.fromDtoToModel()
                )
            )
        )
        runBlocking {
            `when`(gitHubRepo.getRepositories()).thenReturn(ResultState.Success(expectedResponse))

            val actualResponse = getRepositoriesUseCase()

            assertEquals(expectedResponse, actualResponse.data)
        }
    }

    @Test
    fun `invoke should return repositories result failure`(){
        runBlocking {
            `when`(gitHubRepo.getRepositories()).thenReturn(ResultState.Failure("No network"))

            // When
            val actualResponse = getRepositoriesUseCase()

            // Then
            assertEquals("No network", actualResponse.message)
        }
    }
}