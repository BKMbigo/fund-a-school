package com.github.bkmbigo.fundaschool.domain.models.firebase

data class User(
    val id: String = "",
    val name: String = "",
    val photoUrl: String? = null,
    val admin: Boolean = false,
    /**
     * Square CustomerID for customer
     */
    val customerId: String? = null
) {

    companion object {
        val AnonymousUser = User(
            id = "",
            name = "Anonymous",
            photoUrl = null,
            admin = false,
            customerId = null
        )
    }
}