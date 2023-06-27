package com.github.bkmbigo.fundaschool.presentation.components.dialog.login

import android.app.Activity
import android.content.IntentSender
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.AuthRepository
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SavePasswordRequest
import com.google.android.gms.auth.api.identity.SignInPassword
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import dev.gitlive.firebase.GoogleAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
internal actual fun rememberLoginHelper(
    authRepository: AuthRepository,
    onCompleteLogin: () -> Unit
): LoginHelper {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val retrievedUser = remember { MutableSharedFlow<Pair<String, String>?>() }

    val googleSignInState = remember { MutableStateFlow(GoogleSignInState.READY) }

    val oneTapClient = remember { Identity.getSignInClient(context) }

    val googleSignInRequest = remember {
        BeginSignInRequest.builder()
//            .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
//                .setSupported(true)
//                .build())
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(WEB_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()
    }

    val passwordSignInRequest = remember {
        BeginSignInRequest
            .builder()
            .setPasswordRequestOptions(
                BeginSignInRequest.PasswordRequestOptions
                    .builder()
                    .setSupported(true)
                    .build()
            )
            .build()
    }

    val savePasswordRequestLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode != Activity.RESULT_OK) {
            Toast.makeText(
                context,
                "Error saving credential",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    val signInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        try {
            val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
            val idToken = credential.googleIdToken
            val username = credential.id
            val password = credential.password

            when {
                idToken != null -> {
                    coroutineScope.launch {
                        googleSignInState.emit(GoogleSignInState.READY)
                        authRepository.signInWithCredential(
                            GoogleAuthProvider.credential(
                                idToken,
                                null
                            )
                        )
                        onCompleteLogin()
                    }
                }

                password != null -> {
                    coroutineScope.launch {
                        retrievedUser.emit(Pair(username, password))
                    }
                }

                else -> {
                    Log.wtf(
                        "LoginHelper",
                        "Sign in launcher returned a result without idToken or password"
                    )
                    googleSignInState.tryEmit(GoogleSignInState.ERROR)
                    Toast.makeText(context, "Unknown error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: ApiException) {
            //loginViewModel.changeLoading(false)
            when (e.statusCode) {
                CommonStatusCodes.CANCELED -> {
                    Toast.makeText(context, "Request Cancelled", Toast.LENGTH_SHORT).show()
                    googleSignInState.tryEmit(GoogleSignInState.ERROR)
                }

                CommonStatusCodes.NETWORK_ERROR -> {
                    Toast.makeText(
                        context,
                        "Google Encountered A Network Error",
                        Toast.LENGTH_SHORT
                    ).show()
                    googleSignInState.tryEmit(GoogleSignInState.READY)
                }

                else -> {
                    googleSignInState.tryEmit(GoogleSignInState.READY)
                    Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        try {
            val result = oneTapClient.beginSignIn(passwordSignInRequest).await()
            signInLauncher.launch(
                IntentSenderRequest.Builder(result.pendingIntent)
                    .build()
            )
        } catch (e: IntentSender.SendIntentException) {
            Log.e(
                "LoginHelper",
                "Failed to launch Password Sign In",
                e
            )
            Toast.makeText(context, "Error launching Sign In", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e(
                "LoginHelper",
                "Unknown ",
                e
            )
            Toast.makeText(context, "No existing Accounts", Toast.LENGTH_SHORT).show()
        }
    }

    return remember {
        object : LoginHelper {
            override val retrievedUsername: Flow<Pair<String, String>?> =
                retrievedUser.asSharedFlow()
            override val loading: StateFlow<GoogleSignInState> = googleSignInState

            override suspend fun signInUsingGoogle() {
                val result = oneTapClient.beginSignIn(googleSignInRequest).await()
                try {
                    googleSignInState.tryEmit(GoogleSignInState.LOADING)
                    signInLauncher.launch(
                        IntentSenderRequest.Builder(result.pendingIntent)
                            .build()
                    )
                } catch (e: IntentSender.SendIntentException) {
                    Log.e(
                        "LoginHelper",
                        "Failed to launch Google sign In",
                        e
                    )
                    Toast.makeText(context, "Error launching Sign In", Toast.LENGTH_SHORT).show()
                }
            }

            override suspend fun savePassword(email: String, password: String) {
                val signInPassword = SignInPassword(email, password)
                val savePasswordRequest = SavePasswordRequest.builder()
                    .setSignInPassword(signInPassword)
                    .build()
                try {
                    val result = Identity.getCredentialSavingClient(context)
                        .savePassword(savePasswordRequest).await()
                    savePasswordRequestLauncher.launch(
                        IntentSenderRequest.Builder(result.pendingIntent)
                            .build()
                    )
                } catch (e: Exception) {
                    Log.e(
                        "LoginHelper",
                        "Error launching save password"
                    )
                }
            }
        }
    }

}