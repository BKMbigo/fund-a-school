package com.github.bkmbigo.fundaschool.presentation.components.sponsorship

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.bkmbigo.fundaschool.domain.models.firebase.Sponsorship

object SponsorshipItem {
    @Composable
    fun HomeSponsorship(
        sponsorship: Sponsorship,
        modifier: Modifier = Modifier,
        onSponsorshipOpened: () -> Unit,
        onDonate: () -> Unit,
        onSubscribe: () -> Unit
    ) = homeSponsorshipItem(
        sponsorship = sponsorship,
        modifier = modifier,
        onSponsorshipOpened = onSponsorshipOpened,
        onDonate = onDonate,
        onSubscribe = onSubscribe
    )
}