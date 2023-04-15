package com.fria.collect.view.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fria.collect.R
import com.fria.collect.model.ui.FriaProfile
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/**
 * Figma - https://www.figma.com/file/dgSLK7Kp4hEYTCHKevNy42/Untitled?node-id=0%3A1&t=cd534yZ1J4k9YoMq-1
 */

@Preview
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    Column {
        Profile(
            modifier = modifier,
            viewModel.member,
        )
        ContentStateFull(viewModel)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Profile(
    modifier: Modifier = Modifier,
    member: List<FriaProfile>,
) {
    HorizontalPager(
        count = member.size,
        modifier = modifier
    ) { page ->
        ProfileImage(member = member[page])
    }
}

@Composable
fun ProfileImage(member: FriaProfile) {
    Image(
        painter = painterResource(member.image),
        contentDescription = "Profile",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape)
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ContentStateFull(viewModel: MainViewModel) {
    val pageState = rememberPagerState()
    ContentTabStateLess(pageState, viewModel.tabPage)
    ContentHorizontalPage(pageState, viewModel)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ContentTabStateLess(
    pagerState: PagerState,
    tabPage: List<String>
    ) {
    val coroutineScope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
    ) {
        tabPage.forEachIndexed { index, title ->
            Tab(
                text = { Text(text = title) },
                selected = index == pagerState.currentPage,
                onClick = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(index)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ContentHorizontalPage(
    pagerState: PagerState,
    viewModel: MainViewModel
) {
    val tabPage = viewModel.tabPage
    HorizontalPager(
        count = tabPage.size,
        state = pagerState
    ) { page ->
        Text(
            text = page.toString()
        )
    }
}