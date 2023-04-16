package com.fria.collect.view.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.fria.collect.model.CurrentVideo
import com.fria.collect.model.SearchResult
import com.fria.collect.model.ui.FriaProfile
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

/**
 * Figma - https://www.figma.com/file/dgSLK7Kp4hEYTCHKevNy42/Untitled?node-id=0%3A1&t=cd534yZ1J4k9YoMq-1
 */

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    Column {
        Profile(
            modifier = modifier,
            viewModel,
        )
        ContentStateFull(viewModel)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Profile(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
) {
    val member = viewModel.member
    HorizontalPager(
        count = member.size,
        modifier = modifier,
    ) { page ->
        LaunchedEffect(page) {
            snapshotFlow { currentPage }.collect {
                viewModel.memberIndexState.value = currentPage
            }
        }
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
    ) { _ ->
        LaunchedEffect(viewModel.memberIndexState.value) {
            viewModel.getCurrentVideo(viewModel.memberIndexState.value)
        }
        viewModel.currentVideoState.value.videoList?.let { currentVideos ->
            YoutubeLazyColumn(currentVideos = currentVideos)
        }
    }
}

@Composable
fun YoutubeLazyColumn(currentVideos: CurrentVideo) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
    ) {
        items(currentVideos.items.size) { index ->
            VideoCard(currentVideos.items[index])
        }
    }
}

@Composable
fun VideoCard(get: SearchResult) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        val item = get.snippet
        Box(
            modifier = Modifier
                .height(100.dp)
                .padding(16.dp),
        ) {
            Image(
                painter = rememberAsyncImagePainter(item.thumbnails.default.url),
                contentDescription = "Current Video",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(item.thumbnails.default.width.dp)
                    .border(2.dp, Color.Gray)
                    .align(Alignment.CenterStart)
            )
            Column(
                modifier = Modifier
                    .padding(start = (item.thumbnails.default.width + 16).dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}