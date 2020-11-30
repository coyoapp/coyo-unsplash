package com.coyoapp.android.unsplash.data

import com.coyoapp.android.unsplash.data.api.Api
import com.coyoapp.android.unsplash.data.api.toImage
import com.coyoapp.android.unsplash.data.persistence.ImageDao
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ImageRepository(
    coroutineCtx: CoroutineContext,
    private val api: Api,
    private val imageDao: ImageDao
) : BaseRepository(coroutineCtx) {

    fun fetchImageList(query: String) {
        launch {
            val images = api.randomPhotos(query, 30)
            imageDao.upsert(images.map { it.toImage() })
        }
    }

    fun getImages() = imageDao.getImages()

    fun getImage(imageId: String) = imageDao.getImage(imageId)

}
