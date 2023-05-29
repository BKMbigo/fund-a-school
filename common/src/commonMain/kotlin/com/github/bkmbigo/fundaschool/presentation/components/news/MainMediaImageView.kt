package com.github.bkmbigo.fundaschool.presentation.components.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.github.bkmbigo.fundaschool.domain.models.Media
import com.github.bkmbigo.fundaschool.domain.utils.MediaType
import com.seiko.imageloader.rememberAsyncImagePainter


@Composable
fun MediaImageView(
    media: Media,
    modifier: Modifier = Modifier,
    options: MainMediaImageViewOptions = MainMediaImageViewOptions()
) {
    when(media.mediaType) {
        MediaType.IMAGE -> {
            Image(
                painter = rememberAsyncImagePainter(media.url),
                contentDescription = null,
                contentScale = options.contentScale,
            )
        }
        MediaType.VIDEO -> {
            Text(
                text = "Video support To be added later",
                modifier = modifier.background(MaterialTheme.colorScheme.tertiaryContainer),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
        MediaType.AUDIO -> {
            Text(
                text = "Audio support To be added later",
                modifier = modifier.background(MaterialTheme.colorScheme.tertiaryContainer),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
        MediaType.EMBEDDED_VIDEO -> {
            Text(
                text = "Video support To be added later",
                modifier = modifier.background(MaterialTheme.colorScheme.tertiaryContainer),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

data class MainMediaImageViewOptions(
    val contentScale: ContentScale = ContentScale.FillWidth
)