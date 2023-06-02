package com.github.bkmbigo.fundaschool.presentation.components.dialog.login

import com.github.bkmbigo.fundaschool.domain.repositories.AuthRepository
import com.github.bkmbigo.fundaschool.domain.utils.AuthError
import com.github.bkmbigo.fundaschool.domain.utils.AuthResponse
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class LoginPresenter(
    private val authRepository: AuthRepository
) {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _events = Channel<String>()
    val events = _events.receiveAsFlow()

    fun changeState() {
        _state.value = _state.value.copy(
            state = if(_state.value.state == LoginDialogState.LOGIN) LoginDialogState.REGISTRATION else LoginDialogState.LOGIN
        )
    }

    suspend fun loginUser(email: String, password: String) {
        if(email.isBlank() || password.isBlank()) {
            _state.value = _state.value.copy(
                emailError = if(email.isBlank()) "Enter a valid email address" else null,
                passwordError = if(password.isBlank()) "Enter your password" else null,
            )
        } else {
            _state.value = _state.value.copy(
                loading = true,
                nameError = null,
                emailError = null,
                passwordError = null
            )
            authRepository.loginUser(email, password).handleResponse()
        }
    }

    suspend fun registerUser(name: String, email: String, password: String) {
        if(name.isBlank() || email.isBlank() || password.isBlank()) {
            _state.value = _state.value.copy(
                nameError = if(name.isBlank()) "Enter your name" else null,
                emailError = if(email.isBlank()) "Enter a valid email address" else null,
                passwordError = if(password.isBlank()) "Enter your password" else null,
            )
        } else {
            _state.value = _state.value.copy(
                loading = true,
                nameError = null,
                emailError = null,
                passwordError = null
            )
            authRepository.registerUser(name, email, password).handleResponse()
        }
    }


    private fun AuthResponse.handleResponse() {
        _state.value = when(this) {
            is AuthResponse.Error -> when(this.error) {
                AuthError.INVALID_CREDENTIALS -> {
                    _state.value.copy(
                        loading = false,
                        emailError = "Invalid credentials",
                        passwordError = "Invalid credentials"
                    )
                }
                AuthError.PASSWORD_WEAK -> {
                    _state.value.copy(
                        loading = false,
                        passwordError = "Weak password"
                    )
                }
                AuthError.USERNAME_ALREADY_EXISTS -> {
                    _state.value.copy(
                        loading = false,
                        emailError = "Account already exists"
                    )
                }
                AuthError.NETWORK_ERROR -> {
                    _events.trySend("Network error")
                    _state.value.copy(
                        loading = false
                    )
                }
                AuthError.UNKNOWN_ERROR -> {
                    _events.trySend("Unknown error")
                    _state.value.copy(
                        loading = false,
                    )
                }
            }
            is AuthResponse.Success -> {
                _state.value.copy(
                    loading = false,
                    state = LoginDialogState.SUCCESS
                )
            }
        }
    }
    fun close() {

    }
}