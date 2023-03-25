package com.mathias8dev.kinotest.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun Center(fillMaxSize: Boolean = true, content: @Composable() ()->Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = if (fillMaxSize) Modifier.fillMaxSize() else Modifier.fillMaxWidth()
    ) {
        content()
    }
}