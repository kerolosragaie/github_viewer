package com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kerollosragaie.githubviewer.R
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.main.components.RepositoriesBody
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.main.components.ThemeSwitcher

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    callbackParams: (ownerName: String?, repoName: String?) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = MaterialTheme.colorScheme.surface,
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                actions = {
                    ThemeSwitcher(
                        size = 40.dp,
                        padding = 5.dp,
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            RepositoriesBody(callbackParams)
        }
    }
}
