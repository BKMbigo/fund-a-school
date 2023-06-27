package com.github.bkmbigo.fundaschool.presentation.components.dialog.login

import androidx.compose.runtime.Composable
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.AuthRepository
import kotlinx.coroutines.flow.Flow

internal interface LoginHelper {

    val retrievedUsername: Flow<Pair<String, String>?>
    suspend fun signInUsingGoogle()
    suspend fun savePassword(email: String, password: String)
}

@Composable
internal expect fun rememberLoginHelper(
    authRepository: AuthRepository,
    onCompleteLogin: () -> Unit
): LoginHelper