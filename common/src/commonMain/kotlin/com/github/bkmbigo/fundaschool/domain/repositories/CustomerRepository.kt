package com.github.bkmbigo.fundaschool.domain.repositories

import com.github.bkmbigo.fundaschool.domain.models.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    suspend fun insertCustomer(customer: Customer)
    suspend fun updateCustomer(customer: Customer)
    suspend fun deleteCustomer(customer: Customer)
    suspend fun getAllCustomers(): List<Customer>
    suspend fun getCustomer(customerId: String): Customer

    fun observeCustomers(): Flow<List<Customer>>
}