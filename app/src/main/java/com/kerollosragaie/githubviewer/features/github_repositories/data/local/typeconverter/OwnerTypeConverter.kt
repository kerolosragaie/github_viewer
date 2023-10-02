package com.kerollosragaie.githubviewer.features.github_repositories.data.local.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.kerollosragaie.githubviewer.features.github_repositories.data.models.entity.OwnerEntity

class OwnerTypeConverter {
    @TypeConverter
    fun fromOwnerEntityToString(owner: OwnerEntity?): String? = Gson().toJson(owner)

    @TypeConverter
    fun fromOwnerStringToOwnerEntity(owner: String?): OwnerEntity? =
        Gson().fromJson(owner, OwnerEntity::class.java)
}