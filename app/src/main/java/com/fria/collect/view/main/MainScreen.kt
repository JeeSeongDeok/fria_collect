package com.fria.collect.view.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

/**
 * Figma - https://www.figma.com/file/dgSLK7Kp4hEYTCHKevNy42/Untitled?node-id=0%3A1&t=cd534yZ1J4k9YoMq-1
 */

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    TabContainer()
    ProfileStateFull()
}

@Composable
fun TabContainer() {

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


@Composable
fun Profile(
    count: Int,
    onCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileImage(member = DataProvider.member[count])
            Row() {
                Button(
                    modifier = modifier.padding(16.dp),
                    onClick = { onCountChange(count - 1) },
                    enabled = count > -1 && count < 5
                ) {
                    Text("이전")
                }
                Button(
                    modifier = modifier.padding(16.dp),
                    onClick = { onCountChange(count + 1) },
                    enabled = count > -1 && count < 5
                ) {
                    Text("다음")
                }
            }
        }
    }
}

@Composable
fun ProfileImage(member: FriaProfile) {
    Row {
        Image(
            painter = painterResource(member.image),
            contentDescription = "Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(300.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )
    }
}