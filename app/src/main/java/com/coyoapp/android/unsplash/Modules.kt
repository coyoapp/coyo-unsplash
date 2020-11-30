package com.coyoapp.android.unsplash

import android.content.res.Resources
import androidx.room.Room
import coil.ImageLoader
import coil.util.CoilUtils
import com.coyoapp.android.unsplash.data.ImageRepository
import com.coyoapp.android.unsplash.data.api.Api
import com.coyoapp.android.unsplash.data.api.AuthInterceptor
import com.coyoapp.android.unsplash.data.persistence.Database
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.coroutines.CoroutineContext

const val NAME_API_ACCESS_KEY = "accessKey"
const val NAME_API_SECRET = "secretKey"

val appModule = module {
    single {
        ImageLoader.Builder(androidContext())
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(androidContext()))
                    .build()
            }
            .crossfade(true)
            .build()
    }
    single { androidContext().resources }

    // Database
    single {
        Room.inMemoryDatabaseBuilder(androidContext(), Database::class.java).build()
    }
    single { get<Database>().imageDao() }

    // Worker coroutine context
    single<CoroutineContext>(named("UI")) {
        Dispatchers.Main
    }
    single<CoroutineContext>(named("IO")) {
        Dispatchers.IO
    }

    // Repositories
    single {
        ImageRepository(coroutineCtx = get(named("IO")), api = get(), imageDao = get())
    }
}

val apiModule = module {
    single(named(NAME_API_ACCESS_KEY)) { get<Resources>().getString(R.string.accessKey) }
    single(named(NAME_API_SECRET)) { get<Resources>().getString(R.string.secretKey) }
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                AuthInterceptor(get(named(NAME_API_SECRET)), get(named(NAME_API_ACCESS_KEY)))
            )
            .addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }
    single<Api> {
        Retrofit.Builder()
            .baseUrl("https://api.unsplash.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get())
            .build()
            .create(Api::class.java)

    }
}
