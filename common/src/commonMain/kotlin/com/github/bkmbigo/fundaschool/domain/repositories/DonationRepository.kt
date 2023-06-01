package com.github.bkmbigo.fundaschool.domain.repositories

import com.github.bkmbigo.fundaschool.domain.models.Donation
import kotlinx.coroutines.flow.Flow

interface DonationRepository {
    suspend fun addDonation(donation: Donation)
    suspend fun updateDonation(donation: Donation)
    suspend fun deleteDonation(donation: Donation)
    suspend fun getDonation(donationId: String): Donation?
    suspend fun getAllDonations(): List<Donation>

    fun observeDonations(): Flow<List<Donation>>

    suspend fun getAllDonationToSchool(schoolId: String): List<Donation>
}