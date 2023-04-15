package com.fria.collect.network.remote


import com.fria.collect.model.CurrentVideoResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface YoutubeApiService {
    @GET("search")
    suspend fun search(
        @Query("part") part: String?,
        @Query("channelId") channelId: String?,
        @Query("order") order: String?,
        @Query("maxResults") maxResults: Int,
        @Query("key") apiKey: String?
    ): CurrentVideoResponse
}