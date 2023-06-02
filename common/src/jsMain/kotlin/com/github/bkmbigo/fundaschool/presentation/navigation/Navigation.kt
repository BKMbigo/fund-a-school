package com.github.bkmbigo.fundaschool.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import app.softwork.routingcompose.BrowserRouter
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MainNavigation() {
    BrowserRouter(initPath = LocalDestinations.HOME.route) {
        route(LocalDestinations.HOME.route) {
            Text(
                text = "Start"
            )
        }

        route(LocalDestinations.ABOUT_US.route) {

        }

        route(LocalDestinations.DONATIONS.route) {

        }

        route(LocalDestinations.PROJECTS.route) {

        }
    }
}