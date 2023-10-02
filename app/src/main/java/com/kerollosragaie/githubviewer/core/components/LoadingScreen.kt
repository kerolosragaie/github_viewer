package com.kerollosragaie.githubviewer.core.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kerollosragaie.githubviewer.core.theme.GitHubViewerTheme

@Composable
fun LoadingScreen(show: Boolean = true) {
    if (show) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator(
                Modifier.wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    GitHubViewerTheme {
        LoadingScreen(true)
    }
}