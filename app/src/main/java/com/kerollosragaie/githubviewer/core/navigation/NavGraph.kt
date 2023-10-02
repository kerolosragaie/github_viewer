package com.kerollosragaie.githubviewer.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.issues.IssuesScreen
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.main.MainScreen
import com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.repository_details.RepositoryDetailsScreen
import com.kerollosragaie.githubviewer.core.utils.Constants.REPO_NAME_ARGUMENT_KEY
import com.kerollosragaie.githubviewer.core.utils.Constants.USER_NAME_ARGUMENT_KEY


@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
    ) {
        composable(
            route = Screen.Main.route,
        ) {
            MainScreen(
                callbackParams = { ownerName, repoName ->
                    navController.navigate(
                        Screen.RepoDetails.passRepoFullName(
                            repoFullName = "$ownerName/$repoName",
                        )
                    )
                }
            )
        }

        composable(
            route = Screen.RepoDetails.route,
            arguments = listOf(
                navArgument(USER_NAME_ARGUMENT_KEY) {
                    type = NavType.StringType
                },
                navArgument(REPO_NAME_ARGUMENT_KEY) {
                    type = NavType.StringType
                },
            )
        ) {
            RepositoryDetailsScreen(
                callbackParams = { ownerName, repoName ->
                    navController.navigate(
                        Screen.Issues.passRepoFullName(repoFullName = "$ownerName/$repoName")
                    )
                },
            )
        }

        composable(
            route = Screen.Issues.route,
            arguments = listOf(
                navArgument(USER_NAME_ARGUMENT_KEY) {
                    type = NavType.StringType
                },
                navArgument(REPO_NAME_ARGUMENT_KEY) {
                    type = NavType.StringType
                },
            )
        ) {
            IssuesScreen()
        }
    }
}