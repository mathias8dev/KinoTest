package com.mathias8dev.kinotest.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay


@Composable
fun KSplashScreen(navigateTo: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Add your splash screen content here
        Text(
            text = "My App",
            modifier = Modifier.align(Alignment.Center),
        )
    }


    LaunchedEffect(Unit) {
        delay(5000)
        navigateTo()
    }
}