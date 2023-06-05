package com.github.bkmbigo.fundaschool.domain.repositories.firebase

import com.github.bkmbigo.fundaschool.domain.models.firebase.Sponsorship
import kotlinx.coroutines.flow.Flow

interface SponsorshipRepository {
    suspend fun insertSponsorship(sponsorship: Sponsorship)
    suspend fun updateSponsorship(sponsorship: Sponsorship)
    suspend fun deleteSponsorship(sponsorship: Sponsorship)
    suspend fun getSponsorship(sponsorshipId: String): Sponsorship?
    suspend fun getAllSponsorships(): List<Sponsorship>
    fun observeSponsorships(): Flow<List<Sponsorship>>
}