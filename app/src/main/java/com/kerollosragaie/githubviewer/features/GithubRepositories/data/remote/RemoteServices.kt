package com.kerollosragaie.githubviewer.features.GithubRepositories.data.remote

import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseIssues.ResponseIssues
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepoDetails.ResponseRepoDetails
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.models.ResponseRepositories.ResponseRepositories
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteServices {
    @GET("repositories")
    suspend fun getRepositories(): ResponseRepositories

    @GET("repos/{owner}/{repo_name}")
    suspend fun getRepoDetails(
        @Path("owner") ownerName: String,
        @Path("repo_name") repoName: String
    ): ResponseRepoDetails

    @GET("repos/{owner}/{repo_name}/issues")
    suspend fun getRepoIssues(
        @Path("owner") ownerName: String,
        @Path("repo_name") repoName: String
    ): ResponseIssues
}