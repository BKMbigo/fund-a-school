package com.github.bkmbigo.fundaschool.presentation.screens

sealed class MainScreenAction {

    object ResetCard: MainScreenAction()
    object DonateToProject: MainScreenAction()
}