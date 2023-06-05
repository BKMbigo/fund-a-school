package com.github.bkmbigo.fundaschool.presentation.screens.donations

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.github.bkmbigo.fundaschool.domain.models.firebase.Donation
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.DonationRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.ProjectRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.UserRepository
import com.github.bkmbigo.fundaschool.presentation.components.donations.DonationListItem
import com.github.bkmbigo.fundaschool.presentation.screen.donations.DonationsScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DonationsScreenModel(
    private val donationRepository: DonationRepository,
    private val userRepository: UserRepository,
    private val projectRepository: ProjectRepository
): ScreenModel {

    private val _state = MutableStateFlow(DonationsScreenState())
    val state = _state.asStateFlow()

    init {
        coroutineScope.launch {
            getDonations()
        }
    }

    private suspend fun getDonations() {
        val donations = donationRepository.getAllDonations()
        _state.value = _state.value.copy(
            topDonations = donations.getTopDonations().mapIndexedNotNull { index, donation ->  donation.generateDonationListItem(index + 1) },
            latestDonations = donations.getLatestDonations().mapIndexedNotNull { index, donation ->  donation.generateDonationListItem(index + 1) }
        )
    }

    private fun List<Donation>.getTopDonations(): List<Donation> =
        this.sortedBy { it.amount }.take(DEFAULT_LIST_SIZE)
    private fun List<Donation>.getLatestDonations(): List<Donation> =
        this.sortedBy { it.timestamp }.take(DEFAULT_LIST_SIZE)

    private suspend fun Donation.generateDonationListItem(index: Int): DonationListItem? {
        val userDetails = userId?.let { userRepository.getUser(it) }
        val projectDetails = projectRepository.getProject(projectId)

        return projectDetails?.let {
            DonationListItem(
                index = index,
                user = userDetails ?: com.github.bkmbigo.fundaschool.domain.models.firebase.User.AnonymousUser,
                project = projectDetails,
                amount = amount
            )
        }
    }

    companion object {
        const val DEFAULT_LIST_SIZE = 10
    }
}