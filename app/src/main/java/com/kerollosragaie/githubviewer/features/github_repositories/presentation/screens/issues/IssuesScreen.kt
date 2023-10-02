package com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.issues

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kerollosragaie.githubviewer.R
import com.kerollosragaie.githubviewer.core.components.ErrorScreen
import com.kerollosragaie.githubviewer.core.components.LoadingScreen
import com.kerollosragaie.githubviewer.core.theme.GitHubViewerTheme
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseIssueItemModel
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.issues.components.IssueItem
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.issues.viewmodel.IssuesUIState
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.issues.viewmodel.IssuesViewModel

@Composable
fun IssuesScreen(
    issuesViewModel: IssuesViewModel = hiltViewModel()
) {
    val issuesState by issuesViewModel.issuesState.collectAsStateWithLifecycle()

    when (issuesState) {
        is IssuesUIState.Error -> {
            ErrorScreen(
                (issuesState as IssuesUIState.Error).errorMessage,
                retryFunc = {
                    issuesViewModel.getIssuesList()
                }
            )
        }

        is IssuesUIState.Loading -> {
            LoadingScreen()
        }

        is IssuesUIState.Success -> {
            val issuesList =
                (issuesState as IssuesUIState.Success).issuesList.collectAsLazyPagingItems()
            IssuesLazyColumn(issuesList)
        }
    }
}

@Composable
fun IssuesLazyColumn(issuesList: LazyPagingItems<ResponseIssueItemModel>) {

    when {
        issuesList.loadState.refresh is LoadState.Error -> {
            ErrorScreen(
                title = stringResource(id = R.string.error),
                message = (issuesList.loadState.refresh as LoadState.Error).error.message
            )
        }

        issuesList.itemCount == 0 && issuesList.loadState.refresh is LoadState.NotLoading -> {
            ErrorScreen(
                title = stringResource(id = R.string.warning),
                message = stringResource(id = R.string.no_data_found)
            )
        }

        issuesList.loadState.refresh is LoadState.Loading -> {
            LoadingScreen()
        }

        issuesList.itemCount > 0 -> {
            Surface {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(issuesList.itemCount) { index ->
                        issuesList[index]?.let { IssueItem(it) }
                    }
                    if (issuesList.loadState.append is LoadState.Loading)
                        item {
                            CircularProgressIndicator()
                        }
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
        IssuesScreen()
    }
}
