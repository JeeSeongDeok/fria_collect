package com.fria.collect.model.ui

import androidx.compose.ui.graphics.Color

data class FriaProfile(
    val name: String,
    val image: Int,
    val channelId: String,
    val bloodType: String,
    val birthDay: String,
    val mbti: String,
    val personalColor: Color,
    val icon: Int,
)
