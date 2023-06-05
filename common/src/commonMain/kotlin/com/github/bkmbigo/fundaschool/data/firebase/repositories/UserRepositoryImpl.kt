package com.github.bkmbigo.fundaschool.data.firebase.repositories

import com.github.bkmbigo.fundaschool.data.firebase.repositories.utils.decodeUser
import com.github.bkmbigo.fundaschool.domain.models.firebase.User
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.UserRepository
import com.github.bkmbigo.fundaschool.utils.LogInfo
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    firestore: FirebaseFirestore
) : UserRepository {
    private val collectionReference = firestore.collection("user")

    override suspend fun insertUser(user: User) {
        collectionReference.document(user.id).set(user)
    }

    override suspend fun updateUser(user: User) =
        collectionReference.document(user.id).update(
            mapOf(
            "name" to user.name,
            "photoUrl" to (user.photoUrl ?: ""),
            "admin" to user.admin,
            "customerId" to (user.customerId ?: "")
            )
        )

    override suspend fun deleteUser(user: User) =
        collectionReference.document(user.id).delete()


    override suspend fun getAllUsers(): List<User> =
        collectionReference.get().documents.mapNotNull { it.data({ decodeUser() }) }


    override suspend fun getUser(customerId: String): User? =
        try {
            val snapshot = collectionReference.document(customerId).get()
            LogInfo("Snapshot isAdmin: ${snapshot.get("isAdmin")}")
            val user = snapshot.data<User>({ decodeUser() })
            LogInfo("User Object: ${user}")
            user
        } catch (e: Exception) {
            LogInfo("Error: Exception getting error")
            null
        }



    override fun observeUsers(): Flow<List<User>> =
        collectionReference.snapshots.map { snapshot -> snapshot.documents.mapNotNull { it.data({ decodeUser() }) } }
}