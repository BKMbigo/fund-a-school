package com.github.bkmbigo.fundaschool.data.repositories

import com.github.bkmbigo.fundaschool.domain.models.School
import com.github.bkmbigo.fundaschool.domain.repositories.SchoolRepository
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SchoolRepositoryImpl(
    firestore: FirebaseFirestore
): SchoolRepository {
    private val collectionReference = firestore.collection("school")

    override suspend fun insertSchool(school: School) {
        val id = collectionReference.add(school).id
        collectionReference.document(id).update(
            "id" to id
        )
    }

    override suspend fun updateSchool(school: School) {
        collectionReference.document(school.id).update(
            "name" to school.name,
            "location" to school.location
        )
    }

    override suspend fun deleteSchool(school: School) {
        collectionReference.document(school.id).delete()
    }

    override suspend fun getSchool(schoolId: String): School =
        collectionReference.document(schoolId).get().data()


    override suspend fun getAllSchools(): List<School> =
        collectionReference.get().documents.map { it.data() }


    override fun observeSchools(): Flow<List<School>> =
        collectionReference.snapshots.map { snapshot -> snapshot.documents.map { it.data() } }

}