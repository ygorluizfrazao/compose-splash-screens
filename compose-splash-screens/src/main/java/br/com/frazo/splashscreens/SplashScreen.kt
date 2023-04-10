package br.com.frazo.splashscreens

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    finished: Boolean,
    beforeFinished: @Composable BoxScope.() -> Unit,
    whenFinished: @Composable () -> Unit
) {
    Box(
        modifier = modifier
    ) {

        val view = LocalView.current
        if (!finished) {
            if (!view.isInEditMode) {
                val currentWindow = (view.context as? Activity)?.window
                currentWindow?.let {
                    SideEffect {
                        WindowCompat.getInsetsController(it, view)
                            .hide(WindowInsetsCompat.Type.statusBars())
                    }
                }
            }

            beforeFinished()
        } else {

            if (!view.isInEditMode) {
                val currentWindow = (view.context as? Activity)?.window
                currentWindow?.let {
                    WindowCompat.getInsetsController(it, view)
                        .show(WindowInsetsCompat.Type.statusBars())
                }
            }

            whenFinished()
        }

    }
}

