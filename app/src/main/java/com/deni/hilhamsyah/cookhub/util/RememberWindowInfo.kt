package com.deni.hilhamsyah.cookhub.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun rememberWindowInfo(): WindowInfo {
    val configuration = LocalConfiguration.current
    return WindowInfo(
        screenWidth = when {
            configuration.screenWidthDp < 600 -> WindowType.COMPACT
            configuration.screenWidthDp < 840 -> WindowType.MEDIUM
            else -> WindowType.EXPANDED
        },
        screenHeight = when {
            configuration.screenWidthDp < 480 -> WindowType.COMPACT
            configuration.screenWidthDp < 900 -> WindowType.MEDIUM
            else -> WindowType.EXPANDED
        },
    )
}

data class WindowInfo(
    val screenWidth: WindowType,
    val screenHeight: WindowType
)

enum class WindowType {
    COMPACT,
    MEDIUM,
    EXPANDED
}