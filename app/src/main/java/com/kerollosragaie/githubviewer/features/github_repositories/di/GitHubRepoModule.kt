package com.kerollosragaie.githubviewer.features.github_repositories.di

import com.kerollosragaie.githubviewer.features.github_repositories.data.local.data_source.LocalDataSource
import com.kerollosragaie.githubviewer.features.github_repositories.data.remote.data_source.RemoteDataSource
import com.kerollosragaie.githubviewer.features.github_repositories.data.repo.GitHubRepoImpl
import com.kerollosragaie.githubviewer.features.github_repositories.domain.repo.GitHubRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object GitHubRepoModule {

    @Provides
    fun provideGitHubRepo(remoteDataSource: RemoteDataSource,localDataSource: LocalDataSource): GitHubRepo =
        GitHubRepoImpl(remoteDataSource,localDataSource)
}