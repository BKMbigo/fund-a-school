package com.github.bkmbigo.fundaschool.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.screen.home.HomeScreenState

class HomeScreen: Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<HomeViewModel>{
            HomeViewModel(
                projectRepository = withKoin(),
                newsRepository = withKoin(),
                donationRepository = withKoin(),
                userRepository = withKoin()
            )
        }

        val state by screenModel.state.collectAsState()

        HomeScreenContent(
            state,
            {}
        )
    }
}