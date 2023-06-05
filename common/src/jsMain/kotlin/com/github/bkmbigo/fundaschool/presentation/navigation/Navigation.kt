package com.github.bkmbigo.fundaschool.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import app.softwork.routingcompose.BrowserRouter
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.screen.home.HomeScreenAction
import com.github.bkmbigo.fundaschool.presentation.screens.home.HomeScreen
import com.github.bkmbigo.fundaschool.presentation.screens.home.HomeScreenPresenter
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MainNavigation() {
    val layoutProperties = LocalLayoutProperty.current

    BrowserRouter(initPath = LocalDestinations.HOME.route) {
        route(LocalDestinations.HOME.route) {

        }

        route(LocalDestinations.ABOUT_US.route) {

        }

        route(LocalDestinations.DONATIONS.route) {

        }

        route(LocalDestinations.PROJECTS.route) {

        }
    }
}