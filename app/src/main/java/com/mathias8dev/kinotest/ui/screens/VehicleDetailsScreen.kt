package com.mathias8dev.kinotest.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mathias8dev.kinotest.R
import com.mathias8dev.kinotest.ui.components.Center
import com.mathias8dev.kinotest.ui.components.KLottieAnimation


@Composable
fun VehicleDetailsScreen(
    vehicleId: Int,
    onBackPressed: () -> Unit = {}
) {
    Center {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            KLottieAnimation(lottieFile = R.raw.jumping, repeatForever = false)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Mon Id est $vehicleId", style = TextStyle(fontSize = 20.sp))
        }
    }
}