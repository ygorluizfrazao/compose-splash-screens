package br.com.frazo.splashscreens

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun CenteredGifAndText(
    modifier: Modifier = Modifier,
    @DrawableRes gifImage: Int,
    gifSize: Size = Size.ORIGINAL,
    contentDescription: String,
    context: Context = LocalContext.current,
    text: String,
    textStyle: TextStyle = LocalTextStyle.current
) {

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(context).data(data = gifImage).apply(block = {
            size(gifSize)
        }).build(), imageLoader = imageLoader
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription
        )
        Text(text = text, style = textStyle, overflow = TextOverflow.Ellipsis)
    }
}