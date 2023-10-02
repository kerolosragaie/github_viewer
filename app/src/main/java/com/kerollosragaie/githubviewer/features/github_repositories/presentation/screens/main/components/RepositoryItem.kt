package com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.main.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kerollosragaie.githubviewer.R
import com.kerollosragaie.githubviewer.core.components.SmallIconWithAmount
import com.kerollosragaie.githubviewer.core.theme.GitHubViewerTheme
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.OwnerModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseRepositoriesItemModel


@Composable
fun RepositoryItem(
    repositoriesItemModel: ResponseRepositoriesItemModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Text(
                text = repositoriesItemModel.name ?: stringResource(R.string.unknown),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = repositoriesItemModel.ownerModel?.login ?: stringResource(R.string.unknown),
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = repositoriesItemModel.description ?: stringResource(R.string.unknown),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))
            SmallIconWithAmount(
                text = stringResource(R.string.unknown),
                painter = rememberVectorPainter(image = Icons.Default.Star),
                modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.End)
            )
        }
    }
}


@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun RepositoryItemPreview() {
    GitHubViewerTheme {
        RepositoryItem(fakeResponseRepositoriesItemModel)
    }
}

private val ownerModel = OwnerModel(
    avatarUrl = "https://github.com/avatar.png",
    id = 5678,
    nodeId = "123456789",
    type = "User",
    url = "https://github.com/githubuser",
    login = "kero"
)

private val fakeResponseRepositoriesItemModel = ResponseRepositoriesItemModel(
    description = "This is a sample repository This is a sample repository This is a sample repository This is a sample repository This is a sample repository This is a sample repository",
    fullName = "githubuser/sample-repo",
    id = 1234,
    name = "sample-repo",
    nodeId = "123456789",
    ownerModel = ownerModel,
    privateRepo = false,
    url = "https://github.com/githubuser/sample-repo"
)
