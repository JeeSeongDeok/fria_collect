package com.fria.collect.view.main.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.fria.collect.model.CurrentVideo
import com.fria.collect.model.SearchResult
import com.fria.collect.ui.theme.GmarketFont
import com.fria.collect.ui.theme.nobel
import com.fria.collect.ui.theme.silver
import com.fria.collect.view.main.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomCardView(
    modifier: Modifier,
    viewModel: MainViewModel,
    profilePageState: PagerState
) {
    val member = viewModel.member[profilePageState.currentPage]
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
            .fillMaxWidth()
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(20.dp, 20.dp, 0.dp, 0.dp)
            ){
                AnimatedVisibility(
                    enter = slideInVertically() + fadeIn(),
                    exit = slideOutVertically() + fadeOut(),
                    visible = !profilePageState.isScrollInProgress,
                ) {
                    Text(
                        text = member.name,
                        fontFamily = GmarketFont,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
                AnimatedVisibility(
                    enter = slideInVertically() + fadeIn(),
                    exit = slideOutVertically() + fadeOut(),
                    visible = !profilePageState.isScrollInProgress,
                ) {
                    Text(
                        text = member.birthDay,
                        color = nobel
                    )
                }
            }
            Spacer(Modifier.weight(.8f))
            AnimatedVisibility(
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut(),
                visible = !profilePageState.isScrollInProgress,
            ) {
                Image(
                    painter = painterResource(member.icon),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AnimatedVisibility(
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut(),
                visible = !profilePageState.isScrollInProgress,
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = member.personalColor
                    ),
                    onClick = {
                        if (viewModel.bottomCardClick == "YOUTUBE") {
                            viewModel.bottomCardClick("NONE")
                        } else {
                            viewModel.bottomCardClick("YOUTUBE")
                            viewModel.getCurrentVideo(viewModel.memberIndexStateVariable)
                        }
                    }
                ) {
                    Text(
                        text = "Youtube",
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            AnimatedVisibility(
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut(),
                visible = !profilePageState.isScrollInProgress,
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = member.personalColor
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
        if (viewModel.bottomCardClick == "YOUTUBE") {
            viewModel.currentVideoState.value.videoList?.let { currentVideos ->
                YoutubeLazyColumn(currentVideos = currentVideos, viewModel)
            }
        }
    }
}

@Composable
fun YoutubeLazyColumn(currentVideos: CurrentVideo, viewModel: MainViewModel) {
    LazyColumn(
        modifier = Modifier
    ) {
        items(currentVideos.items.size) { index ->
            VideoCard(currentVideos.items[index], viewModel)
        }
    }
}

@Composable
fun VideoCard(
    currentVideo: SearchResult,
    viewModel: MainViewModel,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = viewModel.member[viewModel.memberIndexStateVariable].personalColor,
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 10.dp)
    ) {
        val item = currentVideo.snippet
        Column(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Image(
                painter = rememberAsyncImagePainter(item.thumbnails.medium.url),
                contentDescription = "Current Video",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(item.thumbnails.medium.height.dp)
                    .border(2.dp, Color.White)
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}