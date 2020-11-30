package com.coyoapp.android.unsplash.ui.list

import androidx.lifecycle.ViewModel
import com.coyoapp.android.unsplash.data.ImageRepository

class ImageListViewModel(private val imageRepository: ImageRepository) : ViewModel() {

    init {
        refresh()
    }

    private fun refresh() {
        imageRepository.fetchImageList("winter")
    }

    fun getImages() = imageRepository.getImages()
}
