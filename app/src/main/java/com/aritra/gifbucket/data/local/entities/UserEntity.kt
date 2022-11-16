package com.aritra.gifbucket.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["actual_user_name", "profile_url"])
data class UserEntity(
    val avatar_url: String,
    val banner_image: String,
    val banner_url: String,
    val description: String,
    val display_name: String,
    val instagram_url: String,
    val is_verified: Boolean,
    val profile_url: String,
    @ColumnInfo(name = "actual_user_name")val username: String,
    val website_url: String
)