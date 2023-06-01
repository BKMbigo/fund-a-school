package com.github.bkmbigo.fundaschool.presentation.components.dialog.login

data class LoginState(
    val state: LoginDialogState = LoginDialogState.LOGIN,
    val loading: Boolean = false,

    val nameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null
)

enum class LoginDialogState {
    LOGIN,
    REGISTRATION,
    SUCCESS
}