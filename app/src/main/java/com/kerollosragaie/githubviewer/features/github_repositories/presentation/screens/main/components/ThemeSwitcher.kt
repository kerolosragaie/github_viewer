package com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.main.components

import android.content.res.Configuration
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kerollosragaie.githubviewer.R
import com.kerollosragaie.githubviewer.core.theme.GitHubViewerTheme

@Composable
fun ThemeSwitcher(
    darkTheme: Boolean = isSystemInDarkTheme(),
    size: Dp = 150.dp,
    imageSize: Dp = size / 3,
    padding: Dp = 10.dp,
    borderWidth: Dp = 1.dp,
    parentShape: Shape = CircleShape,
    toggleShape: Shape = CircleShape,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 300),
) {
    val offset by animateDpAsState(
        targetValue = if (darkTheme) 0.dp else size,
        animationSpec = animationSpec
    )

    Box(modifier = Modifier
        .width(size * 2)
        .height(size)
        .clip(shape = parentShape)
        .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset)
                .padding(all = padding)
                .clip(shape = toggleShape)
                .background(MaterialTheme.colorScheme.primary)
        )
        Row(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = borderWidth,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    shape = parentShape
                )
        ) {
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(imageSize),
                    painter = painterResource(id = R.drawable.ic_night_mode),
                    contentDescription = "Theme Icon",
                    colorFilter = ColorFilter.tint(
                        if (darkTheme) MaterialTheme.colorScheme.secondaryContainer
                        else MaterialTheme.colorScheme.primary
                    )
                )
            }
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(imageSize),
                    painter = painterResource(id = R.drawable.ic_light_mode),
                    contentDescription = "Theme Icon",
                    colorFilter = ColorFilter.tint(
                        if (darkTheme) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.secondaryContainer
                    )
                )
            }
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
fun ThemeSwitcherPreview() {
    GitHubViewerTheme {
        ThemeSwitcher(
            size = 40.dp,
            padding = 5.dp,
        )
    }
}