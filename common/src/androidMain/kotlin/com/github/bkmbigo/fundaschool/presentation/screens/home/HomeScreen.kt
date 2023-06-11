package com.github.bkmbigo.fundaschool.presentation.screens.home

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.screens.admin.AdminScreen
import com.github.bkmbigo.fundaschool.presentation.screens.donations.DonationsScreen
import com.github.bkmbigo.fundaschool.presentation.screens.news.NewsScreen
import com.github.bkmbigo.fundaschool.presentation.screens.project.ProjectScreen
import com.github.bkmbigo.fundaschool.presentation.screens.projects.ProjectsScreen


class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current

        val screenModel = remember { HomeScreenModel(withKoin(), withKoin(), withKoin(), withKoin()) }

        val state by screenModel.state.collectAsState()

        HomeScreenContent(
            state = state,
            onAction = { action ->
                when (action) {
                    HomeScreenAction.NavigateToAboutUs -> {}
                    HomeScreenAction.NavigateToAdmin -> {
                        navigator.push(AdminScreen())
                    }

                    HomeScreenAction.NavigateToDonations -> {
                        navigator.push(DonationsScreen())
                    }

                    HomeScreenAction.NavigateToProjects -> {
                        navigator.push(ProjectsScreen())
                    }

                    is HomeScreenAction.NavigateToNews -> {
                        navigator.push(NewsScreen(action.news))
                    }

                    is HomeScreenAction.NavigateToProject -> {
                        navigator.push(ProjectScreen(action.project))
                    }

                    is HomeScreenAction.Search -> {

                    }
                    HomeScreenAction.AcceptPendingDonation -> {
                        //CardEntry.startCardEntryActivity(context as Activity, false)
                    }
                    HomeScreenAction.ClearPendingDonation -> {
                        screenModel.clearPendingDonation()
                    }
                }
            }
        )
    }
}