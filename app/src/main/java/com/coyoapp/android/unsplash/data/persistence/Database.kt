package com.coyoapp.android.unsplash.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.coyoapp.android.unsplash.data.model.Image
import com.coyoapp.android.unsplash.data.persistence.Database.Companion.DATABASE_VERSION

@Database(
    entities = [
        Image::class,
    ],
    version = DATABASE_VERSION
)
abstract class Database : RoomDatabase() {

    abstract fun imageDao(): ImageDao

    companion object {
        const val DATABASE_VERSION = 1
    }
}
