package com.github.bkmbigo.fundaschool.domain.utils

sealed class AuthResponse{
    class Success(val userId: String): AuthResponse()
    class Error(val error: AuthError): AuthResponse()
}
