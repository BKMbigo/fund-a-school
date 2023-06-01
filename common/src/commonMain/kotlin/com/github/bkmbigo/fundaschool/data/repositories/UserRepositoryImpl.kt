package com.github.bkmbigo.fundaschool.data.repositories

import com.github.bkmbigo.fundaschool.domain.models.User
import com.github.bkmbigo.fundaschool.domain.repositories.UserRepository
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
            "name" to user.name,
            "photoUrl" to user.photoUrl,
            "isAdmin" to user.isAdmin,
            "customerId" to user.customerId
        )

    override suspend fun deleteUser(user: User) =
        collectionReference.document(user.id).delete()


    override suspend fun getAllUsers(): List<User> =
        collectionReference.get().documents.map { it.data() }


    override suspend fun getUser(customerId: String): User? =
        try {
            collectionReference.document(customerId).get().data<User>()
        } catch (e: Exception) {
            null
        }



    override fun observeUsers(): Flow<List<User>> =
        collectionReference.snapshots.map { snapshot -> snapshot.documents.map { it.data() } }
}