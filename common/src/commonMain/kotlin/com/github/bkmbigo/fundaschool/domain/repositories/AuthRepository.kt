package com.github.bkmbigo.fundaschool.domain.repositories

import com.github.bkmbigo.fundaschool.domain.models.User
import com.github.bkmbigo.fundaschool.domain.utils.AuthResponse
import dev.gitlive.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): AuthResponse
    suspend fun registerUser(name: String, email: String, password: String): AuthResponse
    fun currentUser(): FirebaseUser?
}