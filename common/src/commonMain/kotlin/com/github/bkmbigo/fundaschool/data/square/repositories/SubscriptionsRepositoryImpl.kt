package com.github.bkmbigo.fundaschool.data.square.repositories

import com.github.bkmbigo.fundaschool.data.square.api.DefaultLocationID
import com.github.bkmbigo.fundaschool.data.square.api.SubscriptionsApi
import com.github.bkmbigo.fundaschool.data.square.dto.CatalogEntryResponseObjectDTO
import com.github.bkmbigo.fundaschool.data.square.dto.EntryCatalogObjectDTO
import com.github.bkmbigo.fundaschool.data.square.dto.CatalogSubscriptionPlan
import com.github.bkmbigo.fundaschool.data.square.dto.CreateSubscriptionDTO
import com.github.bkmbigo.fundaschool.data.square.dto.MoneyDTO
import com.github.bkmbigo.fundaschool.data.square.dto.SubscriptionDTO
import com.github.bkmbigo.fundaschool.data.square.dto.SubscriptionPhaseDTO
import com.github.bkmbigo.fundaschool.data.square.dto.UpsertCatalogDTO
import com.github.bkmbigo.fundaschool.data.square.utils.generateUUID
import com.github.bkmbigo.fundaschool.data.square.utils.safeApiCall
import com.github.bkmbigo.fundaschool.data.square.utils.wrapSquareResponse
import com.github.bkmbigo.fundaschool.domain.repositories.square.SquareResponse
import com.github.bkmbigo.fundaschool.domain.repositories.square.SubscriptionCadence
import com.github.bkmbigo.fundaschool.domain.repositories.square.SubscriptionsRepository
import io.ktor.client.call.body

class SubscriptionsRepositoryImpl(
    private val subscriptionsApi: SubscriptionsApi
): SubscriptionsRepository {
    override suspend fun createCatalog(
        id: String,
        subscriptionName: String,
        cadence: SubscriptionCadence,
        periods: Int,
        money: Float
    ): SquareResponse<EntryCatalogObjectDTO> = wrapSquareResponse {
        safeApiCall {
            subscriptionsApi.createCatalog(
                UpsertCatalogDTO(
                    idempotency_key = generateUUID(),
                    _object = EntryCatalogObjectDTO(
                        type = "SUBSCRIPTION_PLAN",
                        id = id,
                        subscription_plan_data = CatalogSubscriptionPlan(
                            name = subscriptionName,
                            phases = listOf(
                                SubscriptionPhaseDTO(
                                    cadence = cadence,
                                    periods = periods,
                                    recurring_price_money = MoneyDTO.wrapMoney(money)
                                )
                            )
                        ),
                    )
                )
            ).body<CatalogEntryResponseObjectDTO>().catalog_object
        }
    }

    override suspend fun createSubscription(
        plan_id: String,
        customer_id: String,
        start_date: String?,
        canceled_date: String?,
        card_id: String?
    ): SquareResponse<SubscriptionDTO> = wrapSquareResponse {
        safeApiCall {
            subscriptionsApi.createSubscription(
                CreateSubscriptionDTO(
                    idempotency_key = generateUUID(),
                    location_id = DefaultLocationID,
                    plan_id = plan_id,
                    customer_id = customer_id,
                    start_date = start_date,
                    canceled_date = canceled_date,
                    card_id = card_id

                )
            ).body()
        }
    }
}