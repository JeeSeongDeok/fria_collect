package com.fria.collect.view.main.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fria.collect.view.main.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * Figma - https://www.figma.com/file/dgSLK7Kp4hEYTCHKevNy42/Untitled?node-id=0%3A1&t=cd534yZ1J4k9YoMq-1
 */



@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val profilePageState = rememberPagerState()
    Box {
        ProfilePager(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(.9f)
                .align(Alignment.TopStart),
            viewModel,
            profilePageState,
        )
        BottomCardView(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(viewModel.cardHeight.value)
                .align(Alignment.BottomStart),
            viewModel,
            profilePageState
        )
    }
}

