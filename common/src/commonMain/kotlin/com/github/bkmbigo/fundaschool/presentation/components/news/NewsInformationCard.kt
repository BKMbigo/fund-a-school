package com.github.bkmbigo.fundaschool.presentation.components.news

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.domain.models.News

@Composable
fun NewsInformationCard(
    news: News,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = news.caption,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Category:",
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = news.category.text,
                    textAlign = TextAlign.End
                )
            }

            Divider(modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp, vertical = 8.dp))

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Author:",
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = news.author,
                    textAlign = TextAlign.End
                )
            }

            Divider(modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp, vertical = 8.dp))

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Posted on:",
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = news.uploadDate.toString(),
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}