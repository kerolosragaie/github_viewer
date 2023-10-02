package com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.repository_details.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kerollosragaie.githubviewer.R
import com.kerollosragaie.githubviewer.core.theme.GitHubViewerTheme

@Composable
fun ShowInfo(
    title: String,
    value: String?,
    modifier: Modifier = Modifier
) {
    val annotatedString = buildAnnotatedString {
        append("$title:")
        withStyle(
            style = SpanStyle(color = MaterialTheme.colorScheme.secondary)
        )
        {
            append(" ${value ?: stringResource(R.string.unknown)}")
        }
    }
    Text(
        text = annotatedString,
        modifier = modifier.padding(vertical = 10.dp)
    )
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
        ShowInfo(
            title = "Owner",
            value = "Kerollos"
        )
    }
}
