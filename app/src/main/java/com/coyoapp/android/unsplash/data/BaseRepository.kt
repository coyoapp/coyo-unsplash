package com.coyoapp.android.unsplash.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseRepository(private val coroutineCtx: CoroutineContext) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = coroutineCtx + Job()

}
