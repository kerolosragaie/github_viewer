package com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kerollosragaie.githubviewer.core.navigation.SetupNavGraph
import com.kerollosragaie.githubviewer.core.theme.GitHubViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubViewerTheme() {
                navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }
    }
}

