package com.github.bkmbigo.fundaschool.data.firebase.repositories

import com.github.bkmbigo.fundaschool.data.firebase.repositories.utils.decodeSchool
import com.github.bkmbigo.fundaschool.data.firebase.repositories.utils.encodeSchool
import com.github.bkmbigo.fundaschool.domain.models.firebase.School
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.SchoolRepository
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SchoolRepositoryImpl(
    firestore: FirebaseFirestore
) : SchoolRepository {
    private val collectionReference = firestore.collection("school")

    override suspend fun insertSchool(school: School) {
        val id = collectionReference.add(school, { it.encodeSchool() }).id
        collectionReference.document(id).update(
            mapOf(
                "id" to id
            )
        )
    }

    override suspend fun updateSchool(school: School) {
        collectionReference.document(school.id).update(
            mapOf(
                "name" to school.name,
                "location" to school.location
            )
        )
    }

    override suspend fun deleteSchool(school: School) {
        collectionReference.document(school.id).delete()
    }

    override suspend fun getSchool(schoolId: String): School? =
        collectionReference.document(schoolId).get().data({ decodeSchool() })


    override suspend fun getAllSchools(): List<School> =
        collectionReference.get().documents.mapNotNull { it.data({ decodeSchool() }) }


    override fun observeSchools(): Flow<List<School>> =
        collectionReference.snapshots.map { snapshot -> snapshot.documents.mapNotNull { it.data({ decodeSchool() }) } }

}