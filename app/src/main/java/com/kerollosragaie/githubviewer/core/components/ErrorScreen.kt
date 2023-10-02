package com.kerollosragaie.githubviewer.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kerollosragaie.githubviewer.R
import com.kerollosragaie.githubviewer.core.theme.GitHubViewerTheme

@Composable
fun ErrorScreen(
    message: String?,
    title: String? = null,
    retryFunc: (() -> Unit)? = null,
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title ?: stringResource(id = R.string.error),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = message ?: stringResource(id = R.string.something_wrong),
                textAlign = TextAlign.Center
            )
            retryFunc?.let {
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { retryFunc() }
                ) {
                    Text(
                        text = "Retry"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    GitHubViewerTheme() {
        ErrorScreen(
            message = "Error",
            retryFunc = {

            }
        )
    }
}