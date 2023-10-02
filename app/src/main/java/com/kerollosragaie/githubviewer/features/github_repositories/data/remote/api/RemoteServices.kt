package com.kerollosragaie.githubviewer.features.github_repositories.data.remote.api

import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseIssueItemDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseRepoDetailsDto
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.dto.ResponseRepositoriesItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteServices {
    @GET("repositories")
    suspend fun getRepositories(): List<ResponseRepositoriesItemDto>

    @GET("repos/{owner}/{repo_name}")
    suspend fun getRepoDetails(
        @Path("owner") ownerName: String,
        @Path("repo_name") repoName: String
    ): ResponseRepoDetailsDto

    @GET("repos/{owner}/{repo_name}/issues")
    suspend fun getRepoIssues(
        @Path("owner") ownerName: String,
        @Path("repo_name") repoName: String,
        @Query("page") page:Int,
        @Query("per_page") per_page:Int,
    ): List<ResponseIssueItemDto>
}