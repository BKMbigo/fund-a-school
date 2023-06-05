package com.github.bkmbigo.fundaschool.presentation.components.news

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.domain.models.firebase.News
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.github.bkmbigo.fundaschool.presentation.utils.applyCustomSize

@Composable
fun NewsInformationCard(
    news: News,
    size: DpSize,
    modifier: Modifier = Modifier
) {
    val layoutProperties = LocalLayoutProperty.current

    OutlinedCard(
        modifier = modifier.applyCustomSize(size),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = news.caption,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = layoutProperties.TextStyle.informationText
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Category:",
                    style = layoutProperties.TextStyle.informationEmphasis
                )

                Text(
                    text = news.category.text,
                    textAlign = TextAlign.End,
                    style = layoutProperties.TextStyle.informationText
                )
            }

            Divider(modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp, vertical = 8.dp))

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Author:",
                    style = layoutProperties.TextStyle.informationEmphasis
                )

                Text(
                    text = news.author,
                    textAlign = TextAlign.End,
                    style = layoutProperties.TextStyle.informationText
                )
            }

            Divider(modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp, vertical = 8.dp))

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Posted on:",
                    style = layoutProperties.TextStyle.informationEmphasis
                )

                Text(
                    text = news.uploadDate.toString(),
                    textAlign = TextAlign.End,
                    style = layoutProperties.TextStyle.informationText
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}