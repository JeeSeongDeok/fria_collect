package com.fria.collect.domain.data.usecase

import com.fria.collect.common.Resource
import com.fria.collect.domain.data.repository.YoutubeRepository
import com.fria.collect.model.CurrentVideo
import com.fria.collect.model.toCurrentVideo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCurrentVideoUseCase @Inject constructor(
    private val repository: YoutubeRepository
) {
    operator fun invoke(
        part: String,
        channelId: String,
        order: String,
        maxInt: Int,
    ): Flow<Resource<CurrentVideo>> = flow {
        try {
            emit(Resource.Loading())
            val currentVideo = repository.getCurrentVideo(part, channelId, order, maxInt).toCurrentVideo()
            emit(Resource.Success(currentVideo))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "예상치 못한 에러"))
        } catch (e: HttpException) {
            emit(Resource.Error("인터넷 연결 실패"))
        }
    }
}