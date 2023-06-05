package dev.gitlive.firebase.firestore

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.FirebaseException
import kotlinx.coroutines.flow.Flow


/** Returns the [FirebaseFirestore] instance of the default [FirebaseApp]. */
//expect val Firebase.firestore: FirebaseFirestore

/** Returns the [FirebaseFirestore] instance of a given [FirebaseApp]. */
expect fun Firebase.firestore(app: FirebaseApp): FirebaseFirestore

expect class FirebaseFirestore {
    fun collection(path: String): CollectionReference
}

expect class CollectionReference: Query {
    suspend fun <T> add(document: T, encoder: (T) -> Any): DocumentReference
    suspend fun document(path: String): DocumentReference
}

expect class DocumentReference {
    val id: String
    val path: String
    suspend fun get(): DocumentSnapshot
    suspend fun set(data: Any)
    suspend fun update(data: Map<String, Any?>)
    suspend fun delete()
}

expect class FirebaseFirestoreException : FirebaseException

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
expect val FirebaseFirestoreException.code: FirestoreExceptionCode
expect enum class FirestoreExceptionCode {
    OK,
    CANCELLED,
    UNKNOWN,
    INVALID_ARGUMENT,
    DEADLINE_EXCEEDED,
    NOT_FOUND,
    ALREADY_EXISTS,
    PERMISSION_DENIED,
    RESOURCE_EXHAUSTED,
    FAILED_PRECONDITION,
    ABORTED,
    OUT_OF_RANGE,
    UNIMPLEMENTED,
    INTERNAL,
    UNAVAILABLE,
    DATA_LOSS,
    UNAUTHENTICATED
}

expect open class Query {
    val snapshots: Flow<QuerySnapshot>
    suspend fun get(): QuerySnapshot
    internal fun _where(field: String, equalTo: Any): Query
    internal fun _where(field: String, lessThan: Any? = null, greaterThan: Any? = null, arrayContains: Any? = null): Query
    internal fun _where(field: String, inArray: List<Any>? = null, arrayContainsAny: List<Any>? = null): Query
    internal fun _orderBy(field: String, direction: Direction): Query
    internal fun _startAfter(vararg fieldValues: Any): Query
    internal fun _startAt(vararg fieldValues: Any): Query
    internal fun _endBefore(vararg fieldValues: Any): Query
    internal fun _endAt(vararg fieldValues: Any): Query
}

fun Query.where(field: String, equalTo: Any?) = _where(field, equalTo)
fun Query.where(field: String, equalTo: DocumentReference) = _where(field, equalTo)
fun Query.where(field: String, lessThan: Any? = null, greaterThan: Any? = null, arrayContains: Any? = null) = _where(field, lessThan, greaterThan, arrayContains)
fun Query.where(field: String, inArray: List<Any>? = null, arrayContainsAny: List<Any>? = null) = _where(field, inArray, arrayContainsAny)
fun Query.orderBy(field: String, direction: Direction = Direction.ASCENDING) = _orderBy(field, direction)
fun Query.startAfter(document: DocumentSnapshot) = _startAfter(document)
fun Query.startAt(document: DocumentSnapshot) = _startAt(document)
fun Query.endBefore(document: DocumentSnapshot) = _endBefore(document)
fun Query.endAt(document: DocumentSnapshot) = _endAt(document)

expect class QuerySnapshot {
    val documents: List<DocumentSnapshot>
}
expect open class DocumentSnapshot {
    fun get(fieldPath: String): Any?
    internal inline fun <reified T> data(decoder: DocumentSnapshot.() -> T? ): T?
//    internal inline fun <T> data(): T?
}
expect enum class Direction {
    ASCENDING,
    DESCENDING
}

expect class QueryDocumentSnapshot<T>: DocumentSnapshot {
    //inline fun <reified T> data(): T
}

expect interface FirebaseDataConverter<T> {
    fun fromFirestoreInstance(snapshot: QueryDocumentSnapshot<T>): T
    fun toFirestoreInstance(modelObject: T): Any
}