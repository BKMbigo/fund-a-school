package com.github.bkmbigo.fundaschool.data.square.dto

import com.github.bkmbigo.fundaschool.domain.repositories.square.SubscriptionCadence
import kotlinx.serialization.Serializable

@Serializable
data class EntryCatalogObjectDTO(
    val type: String,
    val id: String,
    val is_deleted: Boolean? = null,
    val subscription_plan_data: CatalogSubscriptionPlan?
)

@Serializable
data class CatalogEntryResponseObjectDTO(
    val catalog_object: EntryCatalogObjectDTO,
    val id_mappings: List<IdMappingDTO>
)

@Serializable
data class IdMappingDTO(
    val client_object_id: String,
    val object_id: String
)


@Serializable
data class CatalogSubscriptionPlan(
    val name: String,
    val phases: List<SubscriptionPhaseDTO>
)

@Serializable
data class SubscriptionPhaseDTO(
    //val uid: String? = null,
    val cadence: SubscriptionCadence,
    val periods: Int,
    val recurring_price_money: MoneyDTO,
    val ordinal: Int? = null
)

