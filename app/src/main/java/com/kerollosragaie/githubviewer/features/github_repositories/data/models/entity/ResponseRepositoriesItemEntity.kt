package com.kerollosragaie.githubviewer.features.github_repositories.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ResponseRepositoriesItemEntity(
    @PrimaryKey
    @ColumnInfo("Id")
    val id: Int?,
    @ColumnInfo("Description")
    val description: String?,
    @ColumnInfo("Full name")
    val fullName: String?,
    @ColumnInfo("Repo name")
    val name: String?,
    @ColumnInfo("Node Id")
    val nodeId: String?,
    @ColumnInfo("Owner")
    val ownerEntity: OwnerEntity?,
    @ColumnInfo("Is private")
    val privateRepo: Boolean?,
    @ColumnInfo("Url")
    val url: String?
)


@Entity
data class OwnerEntity(
    @PrimaryKey
    @ColumnInfo("Id")
    val id: Int?,
    @ColumnInfo("AvatarUrl ")
    val avatarUrl: String?,
    @ColumnInfo("Node Id")
    val nodeId: String?,
    @ColumnInfo("Type")
    val type: String?,
    @ColumnInfo("Url")
    val url: String?,
    @ColumnInfo("Login")
    val login: String?
)
