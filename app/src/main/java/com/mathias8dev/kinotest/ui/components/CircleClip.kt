package com.mathias8dev.kinotest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CircleClip(modifier: Modifier = Modifier, content: @Composable() () -> Unit) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color = Color.LightGray)
            .size(32.dp)
            .then(modifier)
    ) {

        content()

    }
}
