package com.fria.collect.view.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    TabStateFull(modifier = modifier)
    ProfileStateFull()
}

object DataProvider {
    val member = listOf(
        FriaProfile("블러비", R.drawable.blur_profile),
        FriaProfile("고여름", R.drawable.summer_profile),
        FriaProfile("바밍", R.drawable.baming_profile),
        FriaProfile("베베리", R.drawable.bebery_profile),
        FriaProfile("전해리", R.drawable.harry_profile),
    )
}

@Preview
@Composable
fun ProfileStateFull(modifier: Modifier = Modifier) {
    var profileCount by remember { mutableStateOf(0) }
    Profile(
        profileCount,
        onCountChange = {
            profileCount = it
        }
    )
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Profile(
    count: Int,
    onCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalPager(
            count = DataProvider.member.size,
            modifier = Modifier
        ) { page ->
            // Content of the pager
            ProfileImage(member = DataProvider.member[page])
        }
    }
}

@Composable
fun ProfileImage(member: FriaProfile) {
    Row(
        modifier = Modifier.heightIn(min = 168.dp)
    ) {
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
}

val tabPage = listOf("틱톡", "유튜브", "아프리카")

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabStateFull(modifier: Modifier) {
    val tabPageState = rememberPagerState()
    TabStateLess(tabPageState)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabStateLess(pos: PagerState) {
    val coroutineScope = rememberCoroutineScope()
        TabRow(selectedTabIndex = pos.currentPage) {
            tabPage.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = index == pos.currentPage,
                    onClick = {

                    }
                )
            }
        }

}