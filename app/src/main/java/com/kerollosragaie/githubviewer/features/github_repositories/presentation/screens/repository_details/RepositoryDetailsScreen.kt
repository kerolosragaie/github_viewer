package com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.repository_details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kerollosragaie.githubviewer.R
import com.kerollosragaie.githubviewer.core.components.ErrorScreen
import com.kerollosragaie.githubviewer.core.components.LoadingScreen
import com.kerollosragaie.githubviewer.core.components.SmallIconWithAmount
import com.kerollosragaie.githubviewer.core.theme.GitHubViewerTheme
import com.kerollosragaie.githubviewer.core.utils.extractDate
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseRepoDetailsModel
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.repository_details.components.ShowInfo
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.repository_details.viewmodel.RepoDetailsUIState
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.repository_details.viewmodel.RepoDetailsViewModel

@Composable
fun RepositoryDetailsScreen(
    callbackParams: (ownerName: String?, repoName: String?) -> Unit,
    repoDetailsViewModel: RepoDetailsViewModel = hiltViewModel()
) {
    val repoDetailsState by repoDetailsViewModel.repoDetails.collectAsStateWithLifecycle()

    when (repoDetailsState) {
        is RepoDetailsUIState.Error -> {
            ErrorScreen(
                (repoDetailsState as RepoDetailsUIState.Error).errorMessage,
                retryFunc = {
                    repoDetailsViewModel.getRepoDetails()
                }
            )
        }

        is RepoDetailsUIState.Loading -> {
            LoadingScreen()
        }

        is RepoDetailsUIState.Success -> {
            val repoDetails = (repoDetailsState as RepoDetailsUIState.Success).repoDetails
            RepoDetailsLazyColumn(
                repoDetails,
                callbackParams
            )
        }
    }
}

@Composable
fun RepoDetailsLazyColumn(
    repoDetails: ResponseRepoDetailsModel,
    callbackParams: (ownerName: String?, repoName: String?) -> Unit,
) {
    Surface {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = repoDetails.name
                            ?: stringResource(id = R.string.unknown),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    SmallIconWithAmount(
                        text = repoDetails.stargazersCount.toString(),
                        painter = rememberVectorPainter(image = Icons.Default.Star),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                ShowInfo(stringResource(R.string.owner), "${repoDetails.ownerModel!!.login}")
                ShowInfo(stringResource(R.string.watchers), "${repoDetails.watchersCount}")
                ShowInfo(stringResource(R.string.forks), "${repoDetails.forksCount}")
                ShowInfo(stringResource(R.string.subscribers), "${repoDetails.subscribersCount}")
                ShowInfo(stringResource(R.string.created_at), extractDate(repoDetails.createdAt))
                ShowInfo(stringResource(R.string.updated_at), extractDate(repoDetails.updatedAt))
                ShowInfo(stringResource(R.string.url), "${repoDetails.url}")
                ShowInfo(stringResource(R.string.description), "${repoDetails.description}")
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {
                        callbackParams(repoDetails.ownerModel.login, repoDetails.name)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                ) {
                    Text(text = stringResource(R.string.view_issues))
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    name = "Light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = true,
    name = "Night mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun RepositoryDetailsScreenPreview() {
    GitHubViewerTheme {
        RepositoryDetailsScreen(
            callbackParams = { _, _ -> }
        )
    }
}
