package com.github.bkmbigo.fundaschool.data.firebase.repositories

import com.github.bkmbigo.fundaschool.data.firebase.repositories.utils.decodeSponsorship
import com.github.bkmbigo.fundaschool.data.firebase.repositories.utils.encodeSponsorship
import com.github.bkmbigo.fundaschool.domain.models.firebase.Sponsorship
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.SponsorshipRepository
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SponsorshipRepositoryImpl(
    firestore: FirebaseFirestore
) : SponsorshipRepository {
    private val collectionReference = firestore.collection("sponsorship")
    private val mediaReference = firestore.collection("media")

    override suspend fun insertSponsorship(sponsorship: Sponsorship) {

        val id = collectionReference.add(sponsorship, { it.encodeSponsorship() }).id
        collectionReference.document(id).update(mapOf("id" to id))
    }

    override suspend fun updateSponsorship(sponsorship: Sponsorship) {
        collectionReference.document(sponsorship.id).update(
            mapOf(
                "title" to sponsorship.title,
                "caption" to sponsorship.caption,
                "text" to sponsorship.text,
                "targetAmount" to sponsorship.targetAmount,
                "currentAmount" to sponsorship.currentAmount,
                "activeSponsors" to sponsorship.activeSponsors,
                "dueDate" to sponsorship.dueDate,
                "mediaUrl" to sponsorship.mediaUrl
            )
        )
    }

    override suspend fun deleteSponsorship(sponsorship: Sponsorship) {
        collectionReference.document(sponsorship.id).delete()
    }

    override suspend fun getSponsorship(sponsorshipId: String) =
        collectionReference.document(sponsorshipId).get().data<Sponsorship>({ decodeSponsorship() })

    override suspend fun getAllSponsorships(): List<Sponsorship> =
        collectionReference.get().documents.mapNotNull { it.data<Sponsorship>({ decodeSponsorship() }) }

    override fun observeSponsorships(): Flow<List<Sponsorship>> =
        collectionReference.snapshots.map { snapshot ->
            snapshot.documents.mapNotNull { it.data<Sponsorship>({ decodeSponsorship() }) }
        }
}