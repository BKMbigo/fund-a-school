package com.github.bkmbigo.fundaschool.presentation.screens.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.utils.MediaType
import com.github.bkmbigo.fundaschool.presentation.components.news.NewsInformationCard
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun LargeScreenNewsContent(
    news: News
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProvideTextStyle(MaterialTheme.typography.headlineLarge) {
            Text(
                text = news.title,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f, true)
                    .verticalScroll(scrollState)
            ) {
                when(news.media.isNotEmpty()) {
                    true -> {
                        when (news.media.first().mediaType){
                            MediaType.IMAGE -> {
                                Image(
                                    painter = rememberAsyncImagePainter(news.media.first().url),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 4.dp, vertical = 8.dp)
                                )
                            }
                            MediaType.VIDEO -> {
                                Text(
                                    text = "Support for videos to be added soon"
                                )
                            }
                            MediaType.AUDIO -> {
                                Text(
                                    text = "Support for audio files to be added soon"
                                )
                            }
                            MediaType.EMBEDDED_VIDEO -> {
                                Text(
                                    text = "Support for videos to be added soon"
                                )
                            }
                        }
                    }
                    false -> {

                    }
                }

                ProvideTextStyle(MaterialTheme.typography.bodySmall) {
                    Text(
                        text = news.text,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .verticalScroll(scrollState)
            ) {
                ProvideTextStyle(MaterialTheme.typography.titleLarge) {
                    Text(
                        text = "Information"
                    )
                }

                NewsInformationCard(
                    news = news,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                        )
                )

                Spacer(modifier = Modifier.height(4.dp))

                ProvideTextStyle(MaterialTheme.typography.titleLarge) {
                    Text(
                        text = "Media"
                    )
                }


            }
        }
    }
}