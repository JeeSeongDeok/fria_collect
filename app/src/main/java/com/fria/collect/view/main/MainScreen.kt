package com.fria.collect.view.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fria.collect.R
import com.fria.collect.model.ui.FriaProfile
import com.fria.collect.ui.theme.FriaCollectTheme
import com.fria.collect.ui.theme.dark42

@Preview
@Composable
fun MainScreen() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = dark42
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            FriaMemberRecyclerView()
        }
    }
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

@Composable
fun FriaMemberRecyclerView() {
    val member = remember { DataProvider.member }
    LazyRow(contentPadding = PaddingValues(16.dp, 8.dp)) {
        items(
            items = member,
            itemContent = { Profile(it) }
        )
    }
}

@Composable
fun Profile(member: FriaProfile) {
    Row {
        Image(
            painter = painterResource(member.image),
            contentDescription = "Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )
    }
}