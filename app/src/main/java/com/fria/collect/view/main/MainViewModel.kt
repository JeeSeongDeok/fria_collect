package com.fria.collect.view.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import com.fria.collect.ui.theme.bamingPersonal
import com.fria.collect.ui.theme.beberyPersonal
import com.fria.collect.ui.theme.blovePersonal
import com.fria.collect.ui.theme.summerPersonal
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
        FriaProfile(
            "블러비",
            R.drawable.blovey_profile,
            KEY_BLOVE,
            "B형",
            "02.11.26",
            "ISTP",
            blovePersonal,
            R.drawable.blovey_icon,
        ),
        FriaProfile(
            "고여름",
            R.drawable.summer_profile,
            KEY_SUMMER,
            "O형",
            "01.08.08",
            "ENFP",
            summerPersonal,
            R.drawable.summer_icon,
        ),
        FriaProfile(
            "바밍",
            R.drawable.baming_profile,
            KEY_BAMING,
            "AB형",
            "00.02.22",
            "INTP",
            bamingPersonal,
            R.drawable.baming_icon,
        ),
        FriaProfile(
            "베베리",
            R.drawable.bebery_profile,
            KEY_BEBERY,
            "O형",
            "99.10.10",
            "ENTJ",
            beberyPersonal,
            R.drawable.bebery_icon,
        ),
    )
    val member: List<FriaProfile>
        get() = _member

    private val _currentVideoState = mutableStateOf(CurrentVideoState())
    val currentVideoState = _currentVideoState

    var memberIndexStateVariable
            by mutableStateOf(savedStateHandle["memberIndex"] ?: 0)
        private set

    var bottomCardClick
            by mutableStateOf(savedStateHandle["bottomCardClick"] ?: "NONE")
        private set

    fun memberIndexChange(index: Int) {
        memberIndexStateVariable = index
    }

    fun bottomCardClick(value: String) {
        bottomCardClick = value
    }

    fun bottomCardViewHeight(): Float {
        return if (bottomCardClick == "NONE")
            .25f
        else
            .5f
    }

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