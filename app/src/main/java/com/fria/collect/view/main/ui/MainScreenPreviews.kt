package com.fria.collect.view.main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.fria.collect.R
import com.fria.collect.ui.theme.GmarketFont
import com.fria.collect.ui.theme.beberyPersonal
import com.fria.collect.ui.theme.nobel
import com.fria.collect.ui.theme.silver

@Preview
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