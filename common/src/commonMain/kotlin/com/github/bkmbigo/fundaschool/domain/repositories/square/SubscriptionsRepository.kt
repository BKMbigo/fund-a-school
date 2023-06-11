package com.github.bkmbigo.fundaschool.domain.repositories.square

import com.github.bkmbigo.fundaschool.data.square.dto.EntryCatalogObjectDTO
import com.github.bkmbigo.fundaschool.data.square.dto.SubscriptionDTO

interface SubscriptionsRepository {
    suspend fun createCatalog(
        id: String,
        subscriptionName: String,
        cadence: SubscriptionCadence,
        periods: Int,
        money: Float,
    ): SquareResponse<EntryCatalogObjectDTO>
    suspend fun createSubscription(
        plan_id: String,
        customer_id: String,
        start_date: String? = null,
        canceled_date: String? = null,
        card_id: String? = null
    ): SquareResponse<SubscriptionDTO>
}