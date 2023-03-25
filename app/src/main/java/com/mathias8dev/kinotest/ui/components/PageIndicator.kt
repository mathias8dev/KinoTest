package com.mathias8dev.kinotest.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mathias8dev.edt.ui.components.CornerDp
import com.mathias8dev.edt.ui.components.RoundedCornerShapeClip
import com.mathias8dev.kinotest.ui.theme.*


@Composable
fun PageIndicatorItem(
    width: Dp,
    color: Color,
) {
    val awidth by animateDpAsState(targetValue = width)
    val acolor by animateColorAsState(targetValue = color)
    RoundedCornerShapeClip(corner = CornerDp(5.dp)) {
        Box(
            modifier = Modifier
                .width(awidth)
                .height(6.dp)
                .background(color = acolor)
        )
    }
}


@Composable
fun PageIndicators(
    pageCount: Int,
    currentPageIndex: Int,
    defaultWidth: Dp = 20.dp,
    selectedWidth: Dp = 60.dp,
    defaultColor: Color = Gray700,
    selectedColor: Color = Gamboge500
) {
    Row {
        for (it in 0 until pageCount) {
            PageIndicatorItem(
                width = if (currentPageIndex == it) selectedWidth else defaultWidth,
                color = if (currentPageIndex == it) selectedColor else defaultColor,
            )
            if (it < pageCount - 1) Spacer(modifier = Modifier.width(5.dp))
        }
    }
}

@Preview
@Composable
fun Previewer() {
    KinoTestPreviewer {
        PageIndicators(pageCount = 4, currentPageIndex = 2)
    }
}