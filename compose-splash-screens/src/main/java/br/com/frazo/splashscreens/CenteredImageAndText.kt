package br.com.frazo.splashscreens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun CenteredImageAndText(
    modifier: Modifier = Modifier,
    @DrawableRes imageDrawableRes: Int,
    contentDescription: String,
    text: String,
    textStyle: TextStyle = LocalTextStyle.current
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageDrawableRes),
            contentDescription = contentDescription
        )
        Text(text = text, style = textStyle, overflow = TextOverflow.Ellipsis)
    }
}