package com.coyoapp.android.unsplash.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/search/photos")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int = 0,
        @Query("per_page") perPage: Int = 30
    ): SearchResponse

    @GET("/photos")
    suspend fun photos(
        @Query("page") page: Int = 0,
        @Query("per_page") perPage: Int = 30
    ): List<ImageItem>

    @GET("/photos/random")
    suspend fun randomPhotos(
        @Query("query") query: String,
        @Query("count") count: Int = 20
    ): List<ImageItem>
}
