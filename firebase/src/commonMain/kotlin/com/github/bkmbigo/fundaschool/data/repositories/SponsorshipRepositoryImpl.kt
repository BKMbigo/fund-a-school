package com.github.bkmbigo.fundaschool.data.repositories

import com.github.bkmbigo.fundaschool.domain.models.Sponsorship
import com.github.bkmbigo.fundaschool.domain.repositories.SponsorshipRepository
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

class SponsorshipRepositoryImpl(
    private val firestore: FirebaseFirestore
): SponsorshipRepository {
    override suspend fun insertSponsorship(sponsorship: Sponsorship) {

    }

    override suspend fun updateSponsorship(sponsorship: Sponsorship) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSponsorship(sponsorship: Sponsorship) {
        TODO("Not yet implemented")
    }

    override suspend fun getSponsorship(sponsorshipId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllSponsorships(): List<Sponsorship> {
        TODO("Not yet implemented")
    }

    override fun observeSponsorships(): Flow<List<Sponsorship>> {
        TODO("Not yet implemented")
    }
}