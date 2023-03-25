package com.mathias8dev.edt.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun RoundedCornerShapeClip(
    modifier: Modifier = Modifier,
    corner: CornerDp = CornerDp(5.dp),
    content: @Composable() () -> Unit
) {
    GenericShapeClip(modifier = modifier, shape = RoundedCornerShape(corner.topStart, corner.topEnd, corner.bottomEnd, corner.bottomStart)) {
        content()
    }
}


@Composable
fun CircleShapeClip(modifier: Modifier = Modifier,
                    content: @Composable() () -> Unit
) {
    GenericShapeClip(modifier = modifier, shape = RoundedCornerShape(50)) {
        content()
    }
}


data class CornerDp(
    val topStart: Dp = 0.dp,
    val topEnd: Dp = 0.dp,
    val bottomEnd: Dp = 0.dp,
    val bottomStart: Dp = 0.dp,
) {
    constructor(all: Dp = 5.dp) : this(topStart = all, topEnd = all, bottomStart = all, bottomEnd = all)
}