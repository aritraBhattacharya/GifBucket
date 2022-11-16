package com.aritra.gifbucket.data.local.entities

import androidx.room.*
import com.aritra.gifbucket.data.remote.dtos.Analytics
import com.aritra.gifbucket.data.remote.dtos.Images
import com.aritra.gifbucket.data.remote.dtos.User

@Entity(tableName = "gif_response")
data class GifResponseEntity(
//    @Ignore
//    val analytics: Analytics,
    val analytics_response_payload: String,
    val bitly_gif_url: String,
    val bitly_url: String,
    val content_url: String,
    val embed_url: String,
    @PrimaryKey @ColumnInfo(name = "gif_id") val id: String,
    //@Ignore val images: Images,
    val import_datetime: String,
    val is_sticker: Int,
    val rating: String,
    val slug: String,
    val source: String,
    val source_post_url: String,
    val source_tld: String,
    val title: String,
    val trending_datetime: String,
    val type: String,
    val url: String,
    @Embedded
    val user: User,
    @ColumnInfo(name = "gif_user_name")val username: String
)