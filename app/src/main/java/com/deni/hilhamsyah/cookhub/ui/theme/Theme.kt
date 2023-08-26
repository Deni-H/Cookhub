package com.deni.hilhamsyah.cookhub.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val darkColorScheme = darkColorScheme(
    primary = primary80,
    secondary = secondary50,
    tertiary = tertiary30,
    background = neutral90,
    surface = neutral90,
    error = error100,
    outline = primary30,
    outlineVariant = iconBorder,

    onPrimary = neutral0,
    onSecondary = neutral0,
    onTertiary = neutral0,
    onBackground = neutral0,
    onSurface = neutral0,
    onError = primary30,
)

private val lightColorScheme = lightColorScheme(
    primary = primary50,
    secondary = secondary80,
    tertiary = tertiary90,
    background = neutral0,
    surface = neutral0,
    error =  error100,
    outline = neutral20,
    outlineVariant = iconBorder,

    onPrimary = neutral100,
    onSecondary = neutral100,
    onTertiary = neutral100,
    onBackground = neutral100,
    onSurface = neutral100,
    onSurfaceVariant = neutral20,
    onError = error100,
)

@Composable
fun CookhubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

//    SideEffect {
//        with(view.context as Activity) {
//            WindowCompat.setDecorFitsSystemWindows(window, false)
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
//            window.statusBarColor = Color.Transparent.toArgb()
//        }
//    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}