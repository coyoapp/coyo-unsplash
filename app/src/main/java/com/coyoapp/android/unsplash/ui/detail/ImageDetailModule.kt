package com.coyoapp.android.unsplash.ui.detail

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val imageDetailModule = module {
    scope<ImageDetailFragment> {
        viewModel {
            ImageDetailViewModel(imageRepository = get())
        }
    }
}
