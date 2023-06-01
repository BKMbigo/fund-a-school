package com.github.bkmbigo.fundaschool.domain.utils

enum class AuthError {
    INVALID_CREDENTIALS,
    PASSWORD_WEAK,
    USERNAME_ALREADY_EXISTS,
    NETWORK_ERROR,
    UNKNOWN_ERROR
}