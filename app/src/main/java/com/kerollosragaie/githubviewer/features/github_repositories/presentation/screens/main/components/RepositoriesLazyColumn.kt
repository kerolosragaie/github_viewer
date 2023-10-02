package com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.kerollosragaie.githubviewer.R
import com.kerollosragaie.githubviewer.core.components.ErrorScreen
import com.kerollosragaie.githubviewer.core.components.LoadingScreen
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseRepositoriesItemModel
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.main.viewmodel.MainViewModel


@ExperimentalMaterial3Api
@Composable
fun RepositoriesLazyColumn(
    repositoriesList: LazyPagingItems<ResponseRepositoriesItemModel>,
    mainViewModel: MainViewModel,
    callbackParams: (ownerName: String?, repoName: String?) -> Unit,
) {
    val searchQuery = mainViewModel.searchQuery.collectAsState()

    when {
        repositoriesList.loadState.refresh is LoadState.Loading -> {
            LoadingScreen()
        }

        repositoriesList.loadState.refresh is LoadState.Error -> {
            ErrorScreen(
                message = (repositoriesList.loadState.refresh as LoadState.Error).error.message,
                retryFunc = {
                    mainViewModel.getRepositoriesList()
                }
            )
        }

        repositoriesList.itemCount > 0 -> {
            Surface {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    item {
                        TextField(
                            value = searchQuery.value,
                            onValueChange = { mainViewModel.updateSearchQuery(it) },
                            label = { Text(stringResource(R.string.search)) },
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            )
                        )
                    }
                    items(repositoriesList.itemCount) {
                        val repositoriesItemModel = repositoriesList[it]

                        if (repositoriesItemModel != null && repositoriesItemModel.name?.contains(
                                searchQuery.value,
                                ignoreCase = true
                            ) == true
                        ) {
                            RepositoryItem(
                                repositoriesItemModel = repositoriesItemModel,
                                modifier = Modifier
                                    .padding(12.dp)
                                    .clickable {
                                        callbackParams(
                                            repositoriesItemModel.ownerModel!!.login,
                                            repositoriesItemModel.name
                                        )
                                    }
                            )
                        }
                    }

                    item {
                        if (repositoriesList.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }

        else -> {
            ErrorScreen(
                message = stringResource(R.string.something_wrong),
                retryFunc = {
                    mainViewModel.getRepositoriesList()
                }
            )
        }
    }
}
