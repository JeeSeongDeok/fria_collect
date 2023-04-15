package com.fria.collect.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fria.collect.R
import com.fria.collect.model.ui.FriaProfile
import com.fria.collect.ui.theme.FriaCollectTheme
import com.fria.collect.ui.theme.dark42
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FriaCollectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = dark42
                ) {
                    MainScreen()
                }
            }
        }
        viewModel.getCurrentVideo()
    }
}