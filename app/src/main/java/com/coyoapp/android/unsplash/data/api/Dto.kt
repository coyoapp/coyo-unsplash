package com.coyoapp.android.unsplash.data.api

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass
import java.io.Serializable

@Keep
@JsonClass(generateAdapter = true)
data class SearchResponse(val results: List<ImageItem>)

@Keep
@JsonClass(generateAdapter = true)
data class PhotosResponse(val data: List<ImageItem>)

@Keep
@JsonClass(generateAdapter = true)
data class ImageItem(
    val id: String,
    val width: Int?,
    val height: Int?,
    val color: String?,
    val description: String?,
    val urls: ImageUrls?,
    val alt_description: String?,
    val bio: String?
) : Serializable

@Keep
@JsonClass(generateAdapter = true)
data class ImageUrls(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
    val thumb: String?
)
