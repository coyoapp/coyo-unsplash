package com.coyoapp.android.unsplash.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coyoapp.android.unsplash.data.model.Image.Companion.TABLE
import java.io.Serializable

@Entity(tableName = TABLE)
data class Image(
    @PrimaryKey val id: String,
    val width: Int?,
    val height: Int?,
    val color: String?,
    val description: String?,
    val thumbUrl: String?,
    val regularUrl: String?,
    val alt_description: String?,
    val bio: String?
) : Serializable {

    companion object {
        const val TABLE = "image"
    }
}
