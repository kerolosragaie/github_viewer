package com.kerollosragaie.githubviewer.features.di

import com.kerollosragaie.githubviewer.features.GithubRepositories.data.remote.RemoteServices
import com.kerollosragaie.githubviewer.features.GithubRepositories.data.repo.GitHubRepoImpl
import com.kerollosragaie.githubviewer.features.GithubRepositories.domain.repo.GitHubRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object GitHubRepoModule {

    @Provides
    fun provideGitHubRepo(remoteServices: RemoteServices): GitHubRepo =
        GitHubRepoImpl(remoteServices)
}