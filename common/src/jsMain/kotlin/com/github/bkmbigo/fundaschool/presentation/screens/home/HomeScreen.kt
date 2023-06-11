package com.github.bkmbigo.fundaschool.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import app.softwork.routingcompose.Router
import app.softwork.routingcompose.navigate
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.navigation.LocalDestinations
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@Composable
fun HomeScreen(
    router: Router
) {
    val coroutineScope = rememberCoroutineScope()
    val presenter = remember {
        HomeScreenPresenter(
            withKoin(),
            withKoin(),
            withKoin(),
            withKoin(),
            coroutineScope
        )
    }

    val state by presenter.state.collectAsState()

    SmallHomeScreenContent(
        state = state,
        onAction = { action ->
            when (action) {
                HomeScreenAction.NavigateToAboutUs -> {
                    router.navigate(
                        to = LocalDestinations.ABOUT_US.route
                    )
                }

                HomeScreenAction.NavigateToAdmin -> {
                    router.navigate(
                        to = LocalDestinations.ADMIN.route
                    )
                }

                HomeScreenAction.NavigateToDonations -> {
                    router.navigate(
                        to = LocalDestinations.DONATIONS.route
                    )
                }

                is HomeScreenAction.NavigateToNews -> {
                    router.navigate(
                        to = LocalDestinations.NEWS.route,
                        parameters = mapOf(
                            "newsId" to action.news.id
                        )
                    )
                }

                is HomeScreenAction.NavigateToProject -> {
                    router.navigate(
                        to = LocalDestinations.PROJECT.route,
                        parameters = mapOf(
                            "newsId" to action.project.id
                        )
                    )
                }

                HomeScreenAction.NavigateToProjects -> {
                    router.navigate(
                        to = LocalDestinations.PROJECTS.route
                    )
                }

                is HomeScreenAction.Search -> {

                }

                HomeScreenAction.AcceptPendingDonation -> {

                }

                HomeScreenAction.ClearPendingDonation -> {

                }
            }
        }
    )
}