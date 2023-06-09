package com.fria.collect.repository

import com.fria.collect.InVisibleConst.KEY_YOUTUBE
import com.fria.collect.domain.data.repository.YoutubeRepository
import com.fria.collect.model.CurrentVideoResponse
import com.fria.collect.network.remote.YoutubeApiService
import javax.inject.Inject


class YoutubeRepositoryImpl @Inject constructor(
    private val api: YoutubeApiService
) : YoutubeRepository {
    override suspend fun getCurrentVideo(
        part: String,
        channelId: String,
        order: String,
        maxResult: Int,
    ): CurrentVideoResponse {
        return api.search(part, channelId, order, maxResult, KEY_YOUTUBE)
    }
}