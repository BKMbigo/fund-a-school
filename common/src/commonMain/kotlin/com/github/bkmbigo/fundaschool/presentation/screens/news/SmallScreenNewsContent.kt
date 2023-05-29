package com.github.bkmbigo.fundaschool.presentation.screens.news

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.domain.models.Media
import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.presentation.components.horizontallist.HorizontalScrollableList
import com.github.bkmbigo.fundaschool.presentation.components.news.MediaImageView
import com.github.bkmbigo.fundaschool.presentation.components.news.NewsInformationCard
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun SmallScreenNewsContent(
    news: News
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp)
    ) {
        Text(
            text = news.title,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = news.caption,
            style = MaterialTheme.typography.bodyMedium
        )


        Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp))

        if (news.media.isNotEmpty()) {
            MediaImageView(
                news.media.first(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 4.dp
                    )
                    .shadow(4.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        Text(
            text = news.text,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 4.dp,
                    vertical = 8.dp
                )
        )

        Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp))

        Text(
            text = "Information",
            style = MaterialTheme.typography.titleLarge
        )

        NewsInformationCard(
            news,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 4.dp,
                    vertical = 4.dp
                )
        )

        Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp))

        Text(
            text = "Media",
            style = MaterialTheme.typography.titleLarge
        )

        HorizontalScrollableList (
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(news.media) { mediaItem ->
                MediaImageView(
                    media = mediaItem,
                    modifier = Modifier
                        .height(200.dp)
                        .width(300.dp)

                )
            }
        }

        Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp))



    }
}