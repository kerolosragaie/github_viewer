package com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseIssues

import com.google.gson.annotations.SerializedName

data class Reactions(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("url") val url: String?
)

fun Reactions.toReactionsModel(): ReactionsModel = ReactionsModel(
    totalCount, url,
)