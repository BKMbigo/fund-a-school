package com.github.bkmbigo.fundaschool.presentation.screens.donations

import com.github.bkmbigo.fundaschool.presentation.components.donations.DonationListItem

data class DonationsScreenState(
    val topDonations: List<DonationListItem> = emptyList(),
    val latestDonations: List<DonationListItem> = emptyList(),
    val isAdmin: Boolean = false
)
