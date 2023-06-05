package com.github.bkmbigo.fundaschool.presentation.components.dialog.login

import com.github.bkmbigo.fundaschool.domain.repositories.firebase.AuthRepository


class LogoutPresenter(
    private val auth: AuthRepository
) {
    suspend fun logoutUser() = auth.logout()
}