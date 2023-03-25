package com.mathias8dev.edt.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape


@Composable
fun GenericShapeClip(
    modifier: Modifier = Modifier,
    shape: Shape,
    content: @Composable() () -> Unit
) {
    Box(modifier = Modifier
        .clip(shape)
        .wrapContentSize()
        .then(modifier)) {
        content()
    }
}