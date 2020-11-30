package com.coyoapp.android.unsplash

import android.app.Application
import com.coyoapp.android.unsplash.ui.detail.imageDetailModule
import com.coyoapp.android.unsplash.ui.list.imageListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoyoUnsplashApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoyoUnsplashApplication)
            // your modules
            modules(
                appModule, apiModule,
                imageListModule, imageDetailModule
            )
        }
    }
}
