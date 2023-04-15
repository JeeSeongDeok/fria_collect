package com.fria.collect.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fria.collect.common.Resource
import com.fria.collect.domain.data.usecase.GetCurrentVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentVideoUseCase: GetCurrentVideoUseCase
) : ViewModel() {
    fun getCurrentVideo() {
        getCurrentVideoUseCase(
            "id,snippet",
            "UCuxfEHCAQuD9Mj8jg_S8utQ",
            "date",
            5,
            ""
        ).onEach { result ->
            when (result) {
                is Resource.Success -> println(result.data.toString())
                is Resource.Error -> println("에러")
                is Resource.Loading -> println("로딩")
            }
        }.launchIn(viewModelScope)
    }
}