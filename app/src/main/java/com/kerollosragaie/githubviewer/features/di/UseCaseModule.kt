package com.kerollosragaie.githubviewer.features.di

import com.kerollosragaie.githubviewer.features.GithubRepositories.domain.repo.GitHubRepo
import com.kerollosragaie.githubviewer.features.GithubRepositories.domain.usecase.GetRepoDetailsUseCase
import com.kerollosragaie.githubviewer.features.GithubRepositories.domain.usecase.GetRepoIssuesUseCase
import com.kerollosragaie.githubviewer.features.GithubRepositories.domain.usecase.GetRepositoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetRepositoriesUseCase(gitHubRepo: GitHubRepo): GetRepositoriesUseCase =
        GetRepositoriesUseCase(gitHubRepo)

    @Provides
    fun provideGetRepoIssuesUseCase(gitHubRepo: GitHubRepo): GetRepoIssuesUseCase =
        GetRepoIssuesUseCase(gitHubRepo)

    @Provides
    fun provideGetRepoDetailsUseCase(gitHubRepo: GitHubRepo): GetRepoDetailsUseCase =
        GetRepoDetailsUseCase(gitHubRepo)
}