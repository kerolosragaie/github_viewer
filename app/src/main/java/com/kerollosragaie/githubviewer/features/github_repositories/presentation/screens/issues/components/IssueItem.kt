package com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.issues.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kerollosragaie.githubviewer.R
import com.kerollosragaie.githubviewer.core.components.SmallIconWithAmount
import com.kerollosragaie.githubviewer.core.theme.GitHubViewerTheme
import com.kerollosragaie.githubviewer.core.utils.extractDate
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseIssueItemModel

@Composable
fun IssueItem(issueItemModel: ResponseIssueItemModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = issueItemModel.ownerModel?.avatarUrl.toString(),
                error = painterResource(R.drawable.ic_error),
                contentDescription = "owner-image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(64.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.size(16.dp))
            IssueContent(issueItemModel)
        }
    }
}

@Composable
private fun IssueContent(issueItemModel: ResponseIssueItemModel) {
    Column {
        Text(
            text = issueItemModel.title.toString(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = issueItemModel.ownerModel?.login ?: stringResource(id = R.string.unknown),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        SmallIconWithAmount(
            text = extractDate(issueItemModel.createdAt),
            painter = rememberVectorPainter(image = Icons.Default.DateRange),
        )
        SmallIconWithAmount(
            text = issueItemModel.state.toString(),
            painter =
            if (issueItemModel.state.toString().contains("open"))
                painterResource(id = R.drawable.ic_open)
            else painterResource(id = R.drawable.ic_close),
        )
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
private fun Preview() {
    GitHubViewerTheme {
        //IssueItem(fakeIssueItem)
    }
}
