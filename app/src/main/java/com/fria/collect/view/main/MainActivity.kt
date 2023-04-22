package com.fria.collect.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.fria.collect.ui.theme.FriaCollectTheme
import com.fria.collect.ui.theme.dark42
import com.fria.collect.view.main.ui.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setImmersiveMode()
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
    }

    private fun setImmersiveMode() {
        window.decorView.windowInsetsController!!.hide(
            android.view.WindowInsets.Type.statusBars()
        )
    }
}