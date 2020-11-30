package com.coyoapp.android.unsplash.ui.detail

import androidx.lifecycle.ViewModel
import com.coyoapp.android.unsplash.data.ImageRepository

class ImageDetailViewModel(private val imageRepository: ImageRepository) : ViewModel() {

    fun getImage(imageId: String) = imageRepository.getImage(imageId)

}
