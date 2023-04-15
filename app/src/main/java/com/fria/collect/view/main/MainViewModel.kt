package com.fria.collect.view.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fria.collect.R
import com.fria.collect.common.Resource
import com.fria.collect.domain.data.usecase.GetCurrentVideoUseCase
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
    val member: List<FriaProfile>
        get() = _member
    private val _member = listOf(
        FriaProfile("블러비", R.drawable.blove_profile),
        FriaProfile("고여름", R.drawable.summer_profile),
        FriaProfile("바밍", R.drawable.baming_profile),
        FriaProfile("베베리", R.drawable.bebery_profile),
    )
    val tabPage: List<String>
        get() = _tabPage
    private val _tabPage = listOf("유튜브", "아프리카")

    fun getCurrentVideo() {
        getCurrentVideoUseCase(
            "id,snippet",
            "UCuxfEHCAQuD9Mj8jg_S8utQ",
            "date",
            5,
        ).onEach { result ->
            when (result) {
                is Resource.Success -> println(result.data.toString())
                is Resource.Error -> println("에러")
                is Resource.Loading -> println("로딩")
            }
        }.launchIn(viewModelScope)
    }
}