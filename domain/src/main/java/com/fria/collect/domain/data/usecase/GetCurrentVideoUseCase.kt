package com.fria.collect.domain.data.usecase

import com.fria.collect.common.Resource
import com.fria.collect.domain.data.repository.YoutubeRepository
import com.fria.collect.model.CurrentVideoResponse
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
    ): Flow<Resource<CurrentVideoResponse>> = flow {
        try {
            emit(Resource.Loading())
            val currentVideoResponse = repository.getCurrentVideo(part, channelId, order, maxInt)
            emit(Resource.Success(currentVideoResponse))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "예상치 못한 에러"))
        } catch (e: HttpException) {
            emit(Resource.Error("인터넷 연결 실패"))
        }
    }
}