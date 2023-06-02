package com.github.bkmbigo.fundaschool.domain.repositories

import com.github.bkmbigo.fundaschool.domain.models.School
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    suspend fun insertSchool(school: School)
    suspend fun updateSchool(school: School)
    suspend fun deleteSchool(school: School)
    suspend fun getSchool(schoolId: String): School?
    suspend fun getAllSchools(): List<School>

    fun observeSchools(): Flow<List<School>>
}