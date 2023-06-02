package com.github.bkmbigo.fundaschool.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val photoUrl: String?
) {
    val isAdmin: Boolean = false

    /**
     * Square CustomerID for customer
     */
    val customerId: String? = null
}