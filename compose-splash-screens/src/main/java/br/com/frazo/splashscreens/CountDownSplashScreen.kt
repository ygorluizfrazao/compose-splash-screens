package br.com.frazo.splashscreens

import android.os.CountDownTimer
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

@Composable
fun CountDownSplashScreen(
    modifier: Modifier = Modifier,
    totalTimeInMillis: Long = 2000,
    notifyMeEveryMillis: Long = 200,
    onNotify: () -> Unit = {},
    beforeFinished: @Composable BoxScope.() -> Unit,
    whenFinished: @Composable () -> Unit
) {

    var finished by rememberSaveable {
        mutableStateOf(false)
    }

    SplashScreen(
        modifier = modifier,
        finished = finished,
        beforeFinished = beforeFinished,
        whenFinished = whenFinished
    )

    LaunchedEffect(key1 = true) {
        object : CountDownTimer(totalTimeInMillis, notifyMeEveryMillis) {
            override fun onTick(millisUntilFinished: Long) {
                onNotify()
            }

            override fun onFinish() {
                finished = true
            }
        }.start()
    }
}