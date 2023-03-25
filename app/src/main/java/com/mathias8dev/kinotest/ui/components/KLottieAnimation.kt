package com.mathias8dev.kinotest.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*

@Composable
fun KLottieAnimation(@RawRes lottieFile: Int, modifier: Modifier = Modifier, repeatForever:Boolean = true) {
    val isLottiePlaying  = true
    val animationSpeed = 1f

    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(lottieFile)
    )


    val lottieAnimation by animateLottieCompositionAsState(
        composition,
        iterations = if (repeatForever) LottieConstants.IterateForever else 1,
        isPlaying = isLottiePlaying,
        speed = animationSpeed,
        restartOnPlay = false
    )
    
    LottieAnimation(
        composition = composition,
        lottieAnimation,
        modifier = Modifier.size(200.dp).then(modifier)
    )
}
