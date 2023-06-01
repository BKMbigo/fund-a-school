package com.github.bkmbigo.fundaschool.data.repositories

import com.github.bkmbigo.fundaschool.domain.models.User
import com.github.bkmbigo.fundaschool.domain.repositories.AuthRepository
import com.github.bkmbigo.fundaschool.domain.repositories.UserRepository
import com.github.bkmbigo.fundaschool.domain.utils.AuthError
import com.github.bkmbigo.fundaschool.domain.utils.AuthResponse
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.*
import dev.gitlive.firebase.firestore.FirebaseFirestore

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val userRepository: UserRepository
) : AuthRepository {
    override suspend fun loginUser(email: String, password: String): AuthResponse = firebase {
        auth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun registerUser(name: String, email: String, password: String): AuthResponse = firebase {
        val response = auth.createUserWithEmailAndPassword(email, password)

        response.user?.let {
            it.updateProfile(displayName = name)
            userRepository.insertUser(
                User(
                    id = it.uid,
                    name = it.displayName ?: "",
                    photoUrl = it.photoURL
                )
            )
        }

        response
    }

    override fun currentUser(): FirebaseUser? = auth.currentUser
}

private suspend fun firebase(function: suspend () -> AuthResult): AuthResponse =
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
