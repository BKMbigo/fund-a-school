package com.github.bkmbigo.fundaschool.presentation.screens.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import app.softwork.routingcompose.Router
import app.softwork.routingcompose.navigate
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.navigation.LocalDestinations
import kotlinx.browser.window

@Composable
fun AdminScreen(
    router: Router
) {
    val coroutineScope = rememberCoroutineScope()

    val presenter = remember {
        AdminScreenPresenter(
            projectRepository = withKoin(),
            newsRepository = withKoin(),
            donationRepository = withKoin(),
            userRepository = withKoin(),
            authRepository = withKoin(),
            coroutineScope = coroutineScope
        )
    }

    val state by presenter.state.collectAsState()

    SmallAdminScreenContent(
        state = state,
        onAction = { action ->
            when (action) {
                AdminScreenAction.AddNews -> {
                    router.navigate(
                        to = LocalDestinations.NEWS.route
                    )
                }

                AdminScreenAction.AddProject -> {
                    router.navigate(
                        to = LocalDestinations.PROJECT.route
                    )
                }

                AdminScreenAction.NavigateUp -> {
                    window.history.back()
                }

                is AdminScreenAction.OpenNews -> {
                    router.navigate(
                        to = LocalDestinations.NEWS.route,
                        parameters = mapOf(
                            "newsId" to action.news.id
                        )
                    )
                }

                is AdminScreenAction.OpenProject -> {
                    router.navigate(
                        to = LocalDestinations.PROJECT.route,
                        parameters = mapOf(
                            "projectId" to action.project.id
                        )
                    )
                }

                is AdminScreenAction.SearchNews -> {
                }

                is AdminScreenAction.SearchProjects -> {

                }
            }
        }
    )

}