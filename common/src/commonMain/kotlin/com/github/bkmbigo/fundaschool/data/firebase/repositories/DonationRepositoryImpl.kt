package com.github.bkmbigo.fundaschool.data.firebase.repositories

import com.github.bkmbigo.fundaschool.domain.models.firebase.Donation
import com.github.bkmbigo.fundaschool.data.firebase.repositories.utils.decodeDonation
import com.github.bkmbigo.fundaschool.data.firebase.repositories.utils.encodeDonation
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.DonationRepository
import com.github.bkmbigo.fundaschool.utils.LogInfo
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DonationRepositoryImpl(
    firestore: FirebaseFirestore
) : DonationRepository {
    private val collectionReference = firestore.collection("donation")

    override suspend fun addDonation(donation: Donation) {
        val id = collectionReference.add(donation, { it.encodeDonation() }).id
        collectionReference.document(id).update(
            mapOf(
                "id" to id
            )
        )
    }

    override suspend fun updateDonation(donation: Donation) {
        collectionReference.document(donation.id).update(
            mapOf(
                "userId" to donation.userId,
                "projectId" to donation.projectId,
                "timestamp" to donation.timestamp,
                "amount" to donation.amount
            )
        )
    }

    override suspend fun deleteDonation(donation: Donation) =
        collectionReference.document(donation.id).delete()

    override suspend fun getDonation(donationId: String): Donation? =
        collectionReference.document(donationId).get().data( {this.decodeDonation()} )

    override suspend fun getAllDonations(): List<Donation> =
        collectionReference.get().documents.map {
            it.data( {
                this.decodeDonation()
            } )
        }.filterNotNull()

    override fun observeDonations(): Flow<List<Donation>> =
        collectionReference.snapshots.map { snapshot -> snapshot.documents.map { it.data( { this.decodeDonation() } ) }.filterNotNull() }


    override suspend fun getAllDonationToSchool(schoolId: String): List<Donation> =
        collectionReference
            .where("schoolId", equalTo = schoolId)
            .get()
            .documents
            .mapNotNull { it.data({ this.decodeDonation() }) }
}