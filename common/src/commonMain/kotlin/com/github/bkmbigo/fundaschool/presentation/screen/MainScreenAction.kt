package com.github.bkmbigo.fundaschool.presentation.screen

import kotlinx.coroutines.flow.Flow

sealed class MainScreenAction {

    object ResetCard: MainScreenAction()
    object DonateToProject: MainScreenAction()
}