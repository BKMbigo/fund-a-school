package com.github.bkmbigo.fundaschool.feature.square

import sqip.CardDetails
import sqip.CardEntryActivityCommand
import sqip.CardNonceBackgroundHandler

// TODO: Develop a more safer approach to handing card details
class CardEntityBackgroundHandler(
    private val onCardEnteredBackground: (CardDetails) -> Unit,
): CardNonceBackgroundHandler {
    override fun handleEnteredCardInBackground(cardDetails: CardDetails): CardEntryActivityCommand {
        onCardEnteredBackground(cardDetails)
        return CardEntryActivityCommand.Finish()
    }
}