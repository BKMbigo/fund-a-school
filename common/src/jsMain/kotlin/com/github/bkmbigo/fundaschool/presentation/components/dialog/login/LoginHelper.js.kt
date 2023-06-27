package com.github.bkmbigo.fundaschool.presentation.components.dialog.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.AuthRepository
import dev.gitlive.firebase.GoogleAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal actual fun rememberLoginHelper(
    authRepository: AuthRepository,
    onCompleteLogin: () -> Unit
): LoginHelper = remember {
    object : LoginHelper {
        override val retrievedUsername: Flow<Pair<String, String>?>
            get() = emptyFlow()

        override val loading: StateFlow<GoogleSignInState> =
            MutableStateFlow(GoogleSignInState.READY)

        override suspend fun signInUsingGoogle() {
            try {
                authRepository.auth.signInWithPopup(GoogleAuthProvider)
                onCompleteLogin()
            } catch (e: Exception) {
                console.log("error message is ${e.message}")
            }

        }

        override suspend fun savePassword(email: String, password: String) {
        }

    }
}