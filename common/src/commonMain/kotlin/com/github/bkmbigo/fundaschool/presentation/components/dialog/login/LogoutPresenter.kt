package com.github.bkmbigo.fundaschool.presentation.components.dialog.login

import dev.gitlive.firebase.auth.FirebaseAuth

class LogoutPresenter(
    private val auth: FirebaseAuth
) {
    suspend fun logoutUser() = auth.signOut()
}