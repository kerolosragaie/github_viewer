package com.kerollosragaie.githubviewer.di

import android.content.Context
import androidx.room.Room
import com.kerollosragaie.githubviewer.features.github_repositories.data.local.dao.RepositoriesDao
import com.kerollosragaie.githubviewer.features.github_repositories.data.local.data_source.LocalDataSource
import com.kerollosragaie.githubviewer.features.github_repositories.data.local.data_source.LocalDataSourceImpl
import com.kerollosragaie.githubviewer.features.github_repositories.data.local.database.RepositoriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideRepositoriesDatabase(@ApplicationContext context: Context): RepositoriesDatabase =
        Room.databaseBuilder(
            context,
            RepositoriesDatabase::class.java,
            "RepositoriesDatabase.db"
        ).build()

    @Provides
    fun provideRepositoriesDao(repositoriesDatabase: RepositoriesDatabase): RepositoriesDao =
        repositoriesDatabase.repositoriesDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(repositoriesDao: RepositoriesDao): LocalDataSource =
        LocalDataSourceImpl(repositoriesDao)
}