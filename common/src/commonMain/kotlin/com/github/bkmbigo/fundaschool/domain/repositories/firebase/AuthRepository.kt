package com.github.bkmbigo.fundaschool.domain.repositories.firebase

import com.github.bkmbigo.fundaschool.domain.utils.AuthResponse
import dev.gitlive.firebase.FirebaseUser

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): AuthResponse
    suspend fun registerUser(name: String, email: String, password: String): AuthResponse
    fun currentUser(): FirebaseUser?
    suspend fun logout()
}