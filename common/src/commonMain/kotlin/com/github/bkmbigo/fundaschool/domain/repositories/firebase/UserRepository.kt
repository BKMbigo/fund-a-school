package com.github.bkmbigo.fundaschool.domain.repositories.firebase

import com.github.bkmbigo.fundaschool.domain.models.firebase.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun getAllUsers(): List<User>
    suspend fun getUser(customerId: String): User?

    fun observeUsers(): Flow<List<User>>
}