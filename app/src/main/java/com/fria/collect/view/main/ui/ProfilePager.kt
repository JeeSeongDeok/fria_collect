package com.fria.collect.view.main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fria.collect.model.ui.FriaProfile
import com.fria.collect.ui.theme.nobel
import com.fria.collect.view.main.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfilePager(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    profilePageState: PagerState,
) {
    val member = viewModel.member
    HorizontalPager(
        count = member.size,
        modifier = modifier,
        state = profilePageState,
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Black)
    ) {
        Image(
            painter = painterResource(member.image),
            contentDescription = "Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .alpha(.5f)
        )
        ProfileMemberInfo(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxHeight(.3f)
                .padding(20.dp),
            "MBTI",
            member.mbti
        )
        ProfileMemberInfo(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxHeight(.3f)
                .padding(20.dp),
            "프리아",
            "소속"
        )
        ProfileMemberInfo(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxHeight(.3f)
                .padding(20.dp),
            "혈액형",
            member.bloodType
        )
    }
}

@Composable
fun ProfileMemberInfo(
    modifier: Modifier,
    title: String,
    description: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = description,
            color = Color.White
        )
        Text(
            text = title,
            color = nobel
        )
    }
}
