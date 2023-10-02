package com.kerollosragaie.githubviewer.features.github_repositories.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kerollosragaie.githubviewer.features.github_repositories.data.local.dao.RepositoriesDao
import com.kerollosragaie.githubviewer.features.github_repositories.data.local.typeconverter.OwnerTypeConverter
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.entity.ResponseRepositoriesItemEntity

@Database(
    entities = [ResponseRepositoriesItemEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(OwnerTypeConverter::class)
abstract class RepositoriesDatabase:RoomDatabase(){
    abstract fun repositoriesDao(): RepositoriesDao
}