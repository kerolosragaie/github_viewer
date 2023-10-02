package com.kerollosragaie.githubviewer.core.navigation

import com.kerollosragaie.githubviewer.core.utils.Constants.MAIN_SCREEN
import com.kerollosragaie.githubviewer.core.utils.Constants.ISSUES_SCREEN
import com.kerollosragaie.githubviewer.core.utils.Constants.REPO_DETAILS_SCREEN
import com.kerollosragaie.githubviewer.core.utils.Constants.REPO_NAME_ARGUMENT_KEY
import com.kerollosragaie.githubviewer.core.utils.Constants.USER_NAME_ARGUMENT_KEY

sealed class Screen(val route: String) {
    object Main : Screen(route = MAIN_SCREEN)

    object RepoDetails :
        Screen(route = "${REPO_DETAILS_SCREEN}/{$USER_NAME_ARGUMENT_KEY}/{$REPO_NAME_ARGUMENT_KEY}") {
        fun passRepoFullName(repoFullName: String): String =
            "$REPO_DETAILS_SCREEN/$repoFullName"
    }

    object Issues :
        Screen(route = "${ISSUES_SCREEN}/{$USER_NAME_ARGUMENT_KEY}/{$REPO_NAME_ARGUMENT_KEY}") {
        fun passRepoFullName(repoFullName: String): String =
            "$ISSUES_SCREEN/$repoFullName"
    }
}
