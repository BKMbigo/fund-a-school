package com.github.bkmbigo.fundaschool.presentation.components.dialog.homeaction

sealed class HomeActionDialogAction {
    object NavigateToHome: HomeActionDialogAction()
    object NavigateToAdmin: HomeActionDialogAction()
    object NavigateToProjects: HomeActionDialogAction()
    object NavigateToDonation: HomeActionDialogAction()
    object NavigateToAboutUs: HomeActionDialogAction()
    object Login: HomeActionDialogAction()
    object EditProfile: HomeActionDialogAction()
    object Logout: HomeActionDialogAction()
}