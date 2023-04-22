package com.fria.collect.view.main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
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
                        containerColor = member.personalColor
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