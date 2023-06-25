package com.github.bkmbigo.fundaschool.data.firebase.repositories

import com.github.bkmbigo.fundaschool.domain.repositories.firebase.AuthRepository
import com.github.bkmbigo.fundaschool.domain.utils.AuthError
import com.github.bkmbigo.fundaschool.domain.utils.AuthResponse
import dev.gitlive.firebase.*

// Made to address issue: https://youtrack.jetbrains.com/issue/KT-48836
class AuthRepositoryImpl(
    private val auth: FirebaseAuth
): AuthRepository {
    override suspend fun loginUser(
        email: String,
        password: String
    ): AuthResponse = firebase {
        auth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun registerUser(
        name: String,
        email: String,
        password: String
    ): AuthResponse = firebase {
        val response = auth.createUserWithEmailAndPassword(email, password)

        response.user?.let {
//            it.updateProfile(displayName = name)
//            userRepository.insertUser(
//                User(
//                    id = it.uid,
//                    name = it.displayName ?: "",
//                    photoUrl = it.photoURL
//                )
//            )
        }

        response
    }

    override suspend fun signInWithCredential(credential: AuthCredential): AuthResponse =
        firebase { auth.signInWithCredential(credential) }


    override fun currentUser(): FirebaseUser? = auth.currentUser
    override suspend fun logout() = auth.signOut()
}

internal suspend fun firebase(function: suspend () -> AuthResult): AuthResponse =
    try {
        function.invoke().user?.let { AuthResponse.Success(it.uid) } ?: AuthResponse.Error(AuthError.UNKNOWN_ERROR)
    } catch (e: FirebaseAuthException) {
        when (e) {
            is FirebaseAuthInvalidCredentialsException -> AuthResponse.Error(AuthError.INVALID_CREDENTIALS)
            is FirebaseAuthInvalidUserException -> AuthResponse.Error(AuthError.INVALID_CREDENTIALS)
            is FirebaseAuthRecentLoginRequiredException -> AuthResponse.Error(AuthError.UNKNOWN_ERROR)
            is FirebaseAuthUserCollisionException -> AuthResponse.Error(AuthError.USERNAME_ALREADY_EXISTS)
            is FirebaseAuthWebException -> AuthResponse.Error(AuthError.NETWORK_ERROR)
            else -> AuthResponse.Error(AuthError.UNKNOWN_ERROR)
        }
    }