package com.kerollosragaie.githubviewer.features.github_repositories.data.remote.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseIssueItemDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.remote.api.RemoteServices
import java.lang.Exception

class IssuesPagingSource(
    private val remoteServices: RemoteServices,
    private val ownerName: String,
    private val repoName: String,
) : PagingSource<Int, ResponseIssueItemDto>() {
    override fun getRefreshKey(state: PagingState<Int, ResponseIssueItemDto>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseIssueItemDto> {
        return try {
            val page = params.key ?: 1
            val response = remoteServices.getRepoIssues(ownerName, repoName, page, 10)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isNotEmpty()) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}