package com.mathias8dev.kinotest.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mathias8dev.kinotest.ui.theme.KinoTestTheme

@Composable
fun KinoTestPreviewer(modifier: Modifier = Modifier, children: @Composable() ()-> Unit) {
    KinoTestTheme {
        Surface(
            modifier = modifier.then(Modifier.fillMaxSize()),
            color = MaterialTheme.colors.background
        ) {
            children()
        }
    }
}