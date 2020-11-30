package com.coyoapp.android.unsplash.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.coyoapp.android.unsplash.data.model.Image

@Dao
abstract class ImageDao : BaseDao<Image>() {

    @Query("SELECT * FROM ${Image.TABLE}")
    abstract fun getImages(): LiveData<List<Image>>

    @Query("SELECT * FROM ${Image.TABLE} WHERE id = :imageId")
    abstract fun getImage(imageId: String): LiveData<Image>
}
