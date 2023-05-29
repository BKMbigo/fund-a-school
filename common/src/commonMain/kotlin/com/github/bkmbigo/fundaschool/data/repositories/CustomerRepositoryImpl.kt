package com.github.bkmbigo.fundaschool.data.repositories

import com.github.bkmbigo.fundaschool.domain.models.Customer
import com.github.bkmbigo.fundaschool.domain.repositories.CustomerRepository
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CustomerRepositoryImpl(
    firestore: FirebaseFirestore
) : CustomerRepository {
    private val collectionReference = firestore.collection("customer")

    override suspend fun insertCustomer(customer: Customer) {
        val id = collectionReference.add(customer).id
        collectionReference.document(id).update(
            "id" to id
        )
    }

    override suspend fun updateCustomer(customer: Customer) =
        collectionReference.document(customer.id).update(
            "name" to customer.name,
            "email" to customer.email,
            "password" to customer.password
        )

    override suspend fun deleteCustomer(customer: Customer) =
        collectionReference.document(customer.id).delete()


    override suspend fun getAllCustomers(): List<Customer> =
        collectionReference.get().documents.map { it.data() }


    override suspend fun getCustomer(customerId: String): Customer =
        collectionReference.document(customerId).get().data()


    override fun observeCustomers(): Flow<List<Customer>> =
        collectionReference.snapshots.map { snapshot -> snapshot.documents.map { it.data() } }
}