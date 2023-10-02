import androidx.paging.PagingData
import com.kerollosragaie.githubviewer.core.utils.ResultState
import com.kerollosragaie.githubviewer.github_repositories.data.fakeIssueItem
import com.kerollosragaie.githubviewer.github_repositories.data.fakeRepoDetails
import com.kerollosragaie.githubviewer.features.github_repositories.data.local.data_source.LocalDataSource
import com.kerollosragaie.githubviewer.features.github_repositories.data.mappers.fromDtoToModel
import com.kerollosragaie.githubviewer.features.github_repositories.data.remote.data_source.RemoteDataSource
import com.kerollosragaie.githubviewer.features.github_repositories.data.repo.GitHubRepoImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GitHubRepoImplTest {

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    private lateinit var gitHubRepo: GitHubRepoImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        gitHubRepo = GitHubRepoImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `test getRepoDetails returns Success`(){
        val ownerName = "owner"
        val repoName = "repo"
        val mockResponse = fakeRepoDetails


        runBlocking {
            `when`(remoteDataSource.getRepoDetails(ownerName, repoName)).thenReturn(mockResponse)

            val result = gitHubRepo.getRepoDetails(ownerName, repoName)

            // Assert the result data
            assertEquals(mockResponse.fromDtoToModel(), (result as ResultState.Success).data)
        }
    }

    @Test
    fun `test getRepoDetails returns Failure`(){
        val ownerName = "owner"
        val repoName = "repo"
        val exceptionMessage = "Error fetching repo details"

        runBlocking {
            `when`(remoteDataSource.getRepoDetails(ownerName, repoName)).thenThrow(RuntimeException(exceptionMessage))

            val result = gitHubRepo.getRepoDetails(ownerName, repoName)

            // Assert the result message
            assertEquals(exceptionMessage, result.message)
        }
    }

    @Test
    fun `test getRepoIssues returns Success`(){
        // Mock remote data source
        val ownerName = "owner"
        val repoName = "repo"
        val issuesList = listOf(
            fakeIssueItem,
            fakeIssueItem
        )
        val mockResponse = flowOf(PagingData.from(issuesList))
        runBlocking {
            `when`(remoteDataSource.getRepoIssues(ownerName, repoName)).thenReturn(mockResponse)

            val result = gitHubRepo.getRepoIssues(ownerName, repoName)

            // Assert the result paging data
            mockResponse.catch {
                    expectedPagingData ->
                result.data!!.catch {
                    assertEquals(expectedPagingData, it)
                }
            }
        }
    }

    @Test
    fun `test getRepoIssues returns Failure`(){
        val ownerName = "owner"
        val repoName = "repo"
        val exceptionMessage = "Error fetching repo issues"

        runBlocking {
            `when`(remoteDataSource.getRepoIssues(ownerName, repoName)).thenThrow(RuntimeException(exceptionMessage))

            val result = gitHubRepo.getRepoIssues(ownerName, repoName)

            // Assert the result message
            assertEquals(exceptionMessage, result.message)
        }
    }
}
