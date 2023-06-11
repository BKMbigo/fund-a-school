package com.github.bkmbigo.fundaschool.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.softwork.routingcompose.BrowserRouter
import app.softwork.routingcompose.HashRouter
import app.softwork.routingcompose.Router
import app.softwork.routingcompose.navigate
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.domain.models.firebase.News
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.NewsRepository
import com.github.bkmbigo.fundaschool.presentation.screens.home.HomeScreenPresenter
import com.github.bkmbigo.fundaschool.presentation.screens.home.HomeScreen
import com.github.bkmbigo.fundaschool.presentation.screens.home.HomeScreenAction
import com.github.bkmbigo.fundaschool.presentation.screens.news.NewsScreenAction
import com.github.bkmbigo.fundaschool.presentation.screens.news.NewsScreenPresenter
import com.github.bkmbigo.fundaschool.presentation.screens.news.SmallNewsScreen
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import kotlinx.browser.window

@Composable
fun MainNavigation() {

}