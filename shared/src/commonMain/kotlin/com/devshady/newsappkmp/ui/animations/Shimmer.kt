package com.devshady.newsappkmp.ui.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val translationAnime by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val baseColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
    val highlightColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.35f)

    val brush = Brush.linearGradient(
        colors = listOf(
            baseColor,
            highlightColor,
            baseColor,
        ),
        start = Offset(translationAnime, translationAnime),
        end = Offset(translationAnime + 250, translationAnime + 250)
    )

    background(brush)
}
