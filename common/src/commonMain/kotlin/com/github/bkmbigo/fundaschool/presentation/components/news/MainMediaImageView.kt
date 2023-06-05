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
import com.github.bkmbigo.fundaschool.domain.utils.MediaType
import com.seiko.imageloader.rememberAsyncImagePainter


@Composable
fun MediaImageView(
    mediaUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillWidth
) = Image(
        painter = rememberAsyncImagePainter(mediaUrl),
        modifier = modifier,
        contentDescription = null,
        contentScale = contentScale,
    )