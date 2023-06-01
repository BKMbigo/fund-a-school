package com.github.bkmbigo.fundaschool.presentation.screen.donations

import com.github.bkmbigo.fundaschool.domain.models.Donation

data class DonationsScreenState(
    val donations: List<Donation> = emptyList(),
    val isAdmin: Boolean = false
)
