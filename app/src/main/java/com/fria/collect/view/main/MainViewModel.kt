package com.fria.collect.view.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fria.collect.R
import com.fria.collect.common.Resource
import com.fria.collect.domain.data.usecase.GetCurrentVideoUseCase
import com.fria.collect.model.remote.ChannelId.KEY_BAMING
import com.fria.collect.model.remote.ChannelId.KEY_BEBERY
import com.fria.collect.model.remote.ChannelId.KEY_BLOVE
import com.fria.collect.model.remote.ChannelId.KEY_SUMMER
import com.fria.collect.model.ui.CurrentVideoState
import com.fria.collect.model.ui.FriaProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCurrentVideoUseCase: GetCurrentVideoUseCase
) : ViewModel() {
    private val _member = listOf(
        FriaProfile("블러비", R.drawable.blove_profile, KEY_BLOVE),
        FriaProfile("고여름", R.drawable.summer_profile, KEY_SUMMER),
        FriaProfile("바밍", R.drawable.baming_profile, KEY_BAMING),
        FriaProfile("베베리", R.drawable.bebery_profile, KEY_BEBERY),
    )
    val member: List<FriaProfile>
        get() = _member

    private val _tabPage = listOf("유튜브", "아프리카")
    val tabPage: List<String>
        get() = _tabPage

    private val _currentVideoState = mutableStateOf(CurrentVideoState())
    val currentVideoState = _currentVideoState

    private val _memberIndexState = mutableStateOf(0)
    val memberIndexState
        get() = _memberIndexState

    fun getCurrentVideo(memberIndex: Int) {
        getCurrentVideoUseCase(
            "id,snippet",
            member[memberIndex].channelId,
            "date",
            5,
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _currentVideoState.value = CurrentVideoState(
                        videoList = result.data
                    )
                }

                is Resource.Error -> {
                    _currentVideoState.value = CurrentVideoState(
                        error = result.message ?: "예상치 못한 에러"
                    )
                }

                is Resource.Loading -> {
                    _currentVideoState.value = CurrentVideoState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}