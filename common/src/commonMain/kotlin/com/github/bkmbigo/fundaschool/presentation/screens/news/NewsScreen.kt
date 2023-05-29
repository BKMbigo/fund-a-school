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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.utils.MediaType
import com.github.bkmbigo.fundaschool.presentation.components.news.NewsInformationCard
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor
import com.seiko.imageloader.rememberAsyncImagePainter

class NewsScreen(
    val news: News,
    val formFactor: FormFactor
) : Screen {
    @Composable
    override fun Content() {
        NewsScreenContent(
            news,
            formFactor
        )
    }

}

@Composable
fun NewsScreenContent(
    news: News,
    formFactor: FormFactor
) {
    when (formFactor) {
        FormFactor.MOBILE -> {
            SmallScreenNewsContent(news)
        }
        FormFactor.SMALL -> {
            SmallScreenNewsContent(news)
        }
        FormFactor.LARGE -> {
            LargeScreenNewsContent(news)
        }
    }
}