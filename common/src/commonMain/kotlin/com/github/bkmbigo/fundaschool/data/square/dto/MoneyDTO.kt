package com.github.bkmbigo.fundaschool.data.square.dto

import kotlinx.serialization.Serializable

@Serializable
data class MoneyDTO(
    val amount: Int,
    val currency: String
) {
    fun toUnwrap() = (amount /100).toFloat()
    companion object {
        fun wrapMoney(amount: Float?) = if(amount!=null) MoneyDTO((amount * 100).toInt(), "USD") else MoneyDTO(0, "USD")
    }
}
