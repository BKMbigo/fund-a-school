package com.github.bkmbigo.fundaschool.data.repositories

import com.github.bkmbigo.fundaschool.domain.models.Donation
import com.github.bkmbigo.fundaschool.domain.repositories.DonationRepository
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DonationRepositoryImpl(
    firestore: FirebaseFirestore
): DonationRepository {
    private val collectionReference = firestore.collection("donation")

    override suspend fun addDonation(donation: Donation) {
        val id = collectionReference.add(donation).id
        collectionReference.document(id).update(
            "id" to id
        )
    }

    override suspend fun updateDonation(donation: Donation) {
        collectionReference.document(donation.id).update(
            "userId" to donation.userId,
            "projectId" to donation.projectId,
            "amount" to donation.amount
        )
    }

    override suspend fun deleteDonation(donation: Donation) =
        collectionReference.document(donation.id).delete()

    override suspend fun getDonation(donationId: String): Donation =
        collectionReference.document(donationId).get().data()

    override suspend fun getAllDonations(): List<Donation> =
        collectionReference.get().documents.map { it.data() }


    override fun observeDonations(): Flow<List<Donation>> =
        collectionReference.snapshots.map { snapshot -> snapshot.documents.map { it.data() } }

    override suspend fun getAllDonationToSchool(schoolId: String): List<Donation> =
        collectionReference
            .where("schoolId", equalTo = schoolId)
            .get()
            .documents
            .map { it.data() }

}