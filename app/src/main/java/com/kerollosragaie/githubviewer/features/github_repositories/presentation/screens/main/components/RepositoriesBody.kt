package com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.main.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.kerollosragaie.githubviewer.core.components.ErrorScreen
import com.kerollosragaie.githubviewer.core.components.LoadingScreen
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.main.viewmodel.MainUIState
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.main.viewmodel.MainViewModel

@ExperimentalMaterial3Api
@Composable
fun RepositoriesBody(
    callbackParams: (ownerName: String?, repoName: String?) -> Unit,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val mainUIState by mainViewModel.mainState.collectAsStateWithLifecycle()
    when (mainUIState) {
        is MainUIState.Error -> {
            ErrorScreen(
                (mainUIState as MainUIState.Error).errorMessage,
                retryFunc = {
                    mainViewModel.getRepositoriesList()
                }
            )
        }

        is MainUIState.Loading -> {
            LoadingScreen()
        }

        is MainUIState.Success -> {
            val reposList =
                (mainUIState as MainUIState.Success).reposList.collectAsLazyPagingItems()
            RepositoriesLazyColumn(
                reposList,
                mainViewModel,
                callbackParams
            )
        }
    }
}
