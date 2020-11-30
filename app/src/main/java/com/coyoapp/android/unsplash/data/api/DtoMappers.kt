package com.coyoapp.android.unsplash.data.api

import com.coyoapp.android.unsplash.data.model.Image

fun ImageItem.toImage() =
    Image(id, width, height, color, description, urls?.thumb, urls?.regular, alt_description, bio)
