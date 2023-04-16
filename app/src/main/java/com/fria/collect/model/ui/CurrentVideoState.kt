package com.fria.collect.model.ui

import com.fria.collect.model.CurrentVideo

data class CurrentVideoState(
    val isLoading: Boolean = false,
    val videoList: CurrentVideo? = null,
    val error: String = "",
)