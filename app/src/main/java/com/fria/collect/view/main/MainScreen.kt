package com.fria.collect.view.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.fria.collect.R
import com.fria.collect.model.CurrentVideo
import com.fria.collect.model.SearchResult
import com.fria.collect.model.ui.FriaProfile
import com.fria.collect.ui.theme.GmarketFont
import com.fria.collect.ui.theme.beberyPersonal
import com.fria.collect.ui.theme.nobel
import com.fria.collect.ui.theme.silver
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

/**
 * Figma - https://www.figma.com/file/dgSLK7Kp4hEYTCHKevNy42/Untitled?node-id=0%3A1&t=cd534yZ1J4k9YoMq-1
 */


@Composable
fun PreviewProfileImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .background(color = Color.Black)
    ) {
        Image(
            painter = painterResource(R.drawable.bebery_profile),
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
                .padding(20.dp),
            "ENTJ",
            "MBTI"
        )
        ProfileMemberInfo(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            "프리아",
            "소속"
        )
        ProfileMemberInfo(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            "혈액형",
            "O형"
        )
    }
}

@Preview
@Composable
fun PreviewCardView() {
    Card(
        shape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = silver
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(30.dp)
        ) {
            Text(
                text = "베베리",
                fontFamily = GmarketFont,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
            Text(
                text = "99.10.10",
                color = nobel
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = beberyPersonal
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Youtube",
                        color = Color.White
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = beberyPersonal
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Afreeca TV",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val profilePageState = rememberPagerState()
    Box {
        Profile(
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Profile(
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


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomCardView(
    modifier: Modifier,
    viewModel: MainViewModel,
    profilePageState: PagerState
) {
    val member = viewModel.member[profilePageState.currentPage]
    val isClick = viewModel.isYoutubeClick
    Card(
        shape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = silver
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(30.dp)
        ) {
            Text(
                text = member.name,
                fontFamily = GmarketFont,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
            Text(
                text = member.birthDay,
                color = nobel
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = beberyPersonal
                    ),
                    onClick = {
                        viewModel.setCardHeight(.5f)
                        isClick.value = true
                        viewModel.getCurrentVideo(viewModel.memberIndexState.value)
                    }
                ) {
                    Text(
                        text = "Youtube",
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = beberyPersonal
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Afreeca TV",
                        color = Color.White
                    )
                }
            }
            if (viewModel.isYoutubeClick.value) {
                viewModel.currentVideoState.value.videoList?.let { currentVideos ->
                    YoutubeLazyColumn(currentVideos = currentVideos)
                }
            }
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