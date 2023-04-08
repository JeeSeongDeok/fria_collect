package com.fria.collect.domain.data.repository

import com.fria.collect.model.SearchResponse

interface YoutubeRepository {
    suspend fun getCurrentVideo(
        part: String,
        channelId: String,
        order: String,
        maxResult: Int,
        key: String
    ) : SearchResponse
}