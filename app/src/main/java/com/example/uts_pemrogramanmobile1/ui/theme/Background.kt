package com.example.uts_pemrogramanmobile1.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val BlueGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF2196F3), // Light Blue
        Color(0xFF1976D2), // Medium Blue
        Color(0xFF0D47A1)  // Dark Blue
    )
)

@Composable
fun BlueGradientBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueGradient)
    ) {
        content()
    }
}
