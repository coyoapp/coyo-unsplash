package com.coyoapp.android.unsplash.ui.list

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val imageListModule = module {
    scope<ImageListFragment> {
        viewModel {
            ImageListViewModel(imageRepository = get())
        }
        scoped {
            ImageListAdapter(imageLoader = get(), onImageClick = get<ImageListFragment>())
        }
    }
}
