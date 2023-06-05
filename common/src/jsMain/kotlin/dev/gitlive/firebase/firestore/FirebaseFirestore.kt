package dev.gitlive.firebase.firestore

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.FirebaseException
import dev.gitlive.firebase.rethrow
import external.firebase.firestore.DocumentData
import kotlinx.coroutines.await
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


/** Returns the [FirebaseFirestore] instance of a given [FirebaseApp]. */
actual fun Firebase.firestore(app: FirebaseApp): FirebaseFirestore =
    FirebaseFirestore(external.firebase.firestore.getFirestore(app.js))

private fun external.firebase.firestore.Firestore.collection(path: String): external.firebase.firestore.CollectionReference<external.firebase.firestore.DocumentData> =
    external.firebase.firestore.collection(this, path)

private suspend fun <T : DocumentData> external.firebase.firestore.CollectionReference<T>.addDoc(data: Any): external.firebase.firestore.DocumentReference<T> =
    external.firebase.firestore.addDoc(this, data).await()

private suspend fun <T : DocumentData> external.firebase.firestore.CollectionReference<T>.doc(path: String): external.firebase.firestore.DocumentReference<T> =
    external.firebase.firestore.doc(this, path).await()

private suspend fun <T : DocumentData> external.firebase.firestore.DocumentReference<T>.getDoc() = external.firebase.firestore.getDoc(this).await()
private suspend fun <T : DocumentData> external.firebase.firestore.DocumentReference<T>.doc(path: String): external.firebase.firestore.DocumentReference<T> =
    external.firebase.firestore.doc(this, path).await()

private suspend fun <T : DocumentData> external.firebase.firestore.DocumentReference<T>.deleteDoc() =
    external.firebase.firestore.deleteDoc(this).await()

private suspend fun <T : DocumentData> external.firebase.firestore.DocumentReference<T>.setDoc(data: Any) =
    external.firebase.firestore.setDoc(this, data).await()

private suspend fun <T : DocumentData> external.firebase.firestore.DocumentReference<T>.updateDoc(data: Map<String, Any?>) =
    external.firebase.firestore.updateDoc(this, data).await()

private suspend fun <T : DocumentData> external.firebase.firestore.Query<T>.getDocs(): external.firebase.firestore.QuerySnapshot<T> =
    external.firebase.firestore.getDocs(this).await()

private fun <T : DocumentData> external.firebase.firestore.Query<T>.onSnapshot(
    onNext: (snapshot: external.firebase.firestore.QuerySnapshot<T>) -> Unit,
    onError: (error: external.firebase.firestore.FirestoreError) -> Unit
) = external.firebase.firestore.onSnapshot(this, onNext, onError)

actual class FirebaseFirestore(private val js: external.firebase.firestore.Firestore) {
    actual fun collection(path: String): CollectionReference = rethrow { CollectionReference(js.collection(path)) }
}

actual class CollectionReference(private val js: external.firebase.firestore.CollectionReference<external.firebase.firestore.DocumentData>): Query(js) {
    actual suspend fun <T> add(document: T, encoder: (T) -> Any): DocumentReference = rethrow {
     //   js.withConverter(document.getFirestoreConverter().toJsFirebaseDataConverter().unsafeCast<js.firestore.FirestoreDataConverter<DocumentData>>())
//        DocumentReference(js.addDoc(document))
        DocumentReference(js.addDoc(encoder(document)))
    }

    actual suspend fun document(path: String): DocumentReference = rethrow {
        DocumentReference(js.doc(path))
    }

}

actual class DocumentReference(private val js: external.firebase.firestore.DocumentReference<external.firebase.firestore.DocumentData>) {
    actual val id: String
        get() = rethrow { js.id }

    actual val path: String
        get() = rethrow { js.path }

    actual suspend fun get(): DocumentSnapshot = rethrow { DocumentSnapshot(js.getDoc()) }
    actual suspend fun delete() = rethrow { js.deleteDoc() }
    actual suspend fun update(data: Map<String, Any?>) = rethrow { js.updateDoc(data) }
    actual suspend fun set(data: Any) = rethrow {
        js.setDoc(data)
    }
}

actual open class Query(private val js: external.firebase.firestore.Query<DocumentData>) {
    actual val snapshots: Flow<QuerySnapshot> = callbackFlow {
        js.onSnapshot(
            { trySend(QuerySnapshot(it)) },
            { close(errorToException(it)) }
        )
        awaitClose { }
    }

    actual suspend fun get(): QuerySnapshot = rethrow {
        QuerySnapshot(js.getDocs())
    }

    internal actual fun _where(field: String, equalTo: Any): Query = rethrow {
        Query(js.where(field, "==", equalTo))
    }

    internal actual fun _where(
        field: String,
        lessThan: Any?,
        greaterThan: Any?,
        arrayContains: Any?
    ): Query = rethrow {
        Query(
            (lessThan?.let { js.where(field, "<", it) } ?: js).let { js2 ->
                (greaterThan?.let { js2.where(field, ">", it) } ?: js2).let { js3 ->
                    arrayContains?.let { js3.where(field, "array-contains", it) } ?: js3
                }
            }
        )
    }

    internal actual fun _where(
        field: String,
        inArray: List<Any>?,
        arrayContainsAny: List<Any>?
    ): Query = rethrow {
        Query(
            (inArray?.let { js.where(field, "in", it.toTypedArray()) } ?: js).let { js2 ->
                arrayContainsAny?.let { js2.where(field, "array-contains-any", it.toTypedArray()) } ?: js2
            }
        )
    }

    internal actual fun _orderBy(
        field: String,
        direction: Direction
    ): Query = rethrow {
        Query(js.orderBy(field, direction.jsString))
    }

    internal actual fun _startAfter(vararg fieldValues: Any): Query = rethrow { Query(js.startBefore(*fieldValues)) }
    internal actual fun _startAt(vararg fieldValues: Any): Query = rethrow { Query(js.startAt(*fieldValues)) }
    internal actual fun _endBefore(vararg fieldValues: Any): Query = rethrow { Query(js.endBefore(*fieldValues)) }
    internal actual fun _endAt(vararg fieldValues: Any): Query = rethrow { Query(js.endAt(*fieldValues)) }

}
actual class FirebaseFirestoreException(cause: Throwable, val code: FirestoreExceptionCode) :
    FirebaseException(code.toString(), cause)

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
actual val FirebaseFirestoreException.code: FirestoreExceptionCode get() = code
inline fun <T, R> T.rethrow(function: T.() -> R): R = dev.gitlive.firebase.firestore.rethrow { function() }

inline fun <R> rethrow(function: () -> R): R {
    try {
        return function()
    } catch (e: Exception) {
        throw e
    } catch (e: dynamic) {
        throw errorToException(e)
    }
}

fun errorToException(e: dynamic) = (e?.code ?: e?.message ?: "")
    .toString()
    .lowercase()
    .let {
        when {
            "cancelled" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.CANCELLED)
            "invalid-argument" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.INVALID_ARGUMENT)
            "deadline-exceeded" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.DEADLINE_EXCEEDED)
            "not-found" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.NOT_FOUND)
            "already-exists" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.ALREADY_EXISTS)
            "permission-denied" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.PERMISSION_DENIED)
            "resource-exhausted" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.RESOURCE_EXHAUSTED)
            "failed-precondition" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.FAILED_PRECONDITION)
            "aborted" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.ABORTED)
            "out-of-range" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.OUT_OF_RANGE)
            "unimplemented" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.UNIMPLEMENTED)
            "internal" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.INTERNAL)
            "unavailable" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.UNAVAILABLE)
            "data-loss" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.DATA_LOSS)
            "unauthenticated" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.UNAUTHENTICATED)
            "unknown" in it -> FirebaseFirestoreException(e, FirestoreExceptionCode.UNKNOWN)
            else -> {
                println("Unknown error code in ${JSON.stringify(e)}")
                FirebaseFirestoreException(e, FirestoreExceptionCode.UNKNOWN)
            }
        }
    }

actual enum class FirestoreExceptionCode {
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

actual class QuerySnapshot(private val js: external.firebase.firestore.QuerySnapshot<DocumentData>) {
    actual val documents: List<DocumentSnapshot>
        get() = js.docs.map { DocumentSnapshot(it) }
}


private fun <T : DocumentData> external.firebase.firestore.Query<T>.query(vararg queryConstraints: external.firebase.firestore.QueryConstraint): external.firebase.firestore.Query<T> =
    external.firebase.firestore.query(this, *queryConstraints)

private fun <T : DocumentData> external.firebase.firestore.Query<T>.orderBy(
    fieldPath: String,
    directionStr: String
): external.firebase.firestore.Query<T> {
    val constraint = external.firebase.firestore.orderBy(fieldPath, directionStr)
    return this.query(constraint)
}

private fun <T : DocumentData> external.firebase.firestore.Query<T>.where(
    fieldPath: String,
    opStr: String,
    value: Any
): external.firebase.firestore.Query<T> {
    val constraint = external.firebase.firestore.where(fieldPath, opStr, value)
    return this.query(constraint)
}

private fun <T : DocumentData> external.firebase.firestore.Query<T>.endAt(vararg fieldValues: Any): external.firebase.firestore.Query<T> {
    val constraint = external.firebase.firestore.endAt(fieldValues)
    return this.query(constraint)
}

private fun <T : DocumentData> external.firebase.firestore.Query<T>.endBefore(vararg fieldValues: Any): external.firebase.firestore.Query<T> {
    val constraint = external.firebase.firestore.endBefore(fieldValues)
    return this.query(constraint)
}

private fun <T : DocumentData> external.firebase.firestore.Query<T>.startAt(vararg fieldValues: Any): external.firebase.firestore.Query<T> {
    val constraint = external.firebase.firestore.startAt(fieldValues)
    return this.query(constraint)
}

private fun <T : DocumentData> external.firebase.firestore.Query<T>.startBefore(vararg fieldValues: Any): external.firebase.firestore.Query<T> {
    val constraint = external.firebase.firestore.startAfter(fieldValues)
    return this.query(constraint)
}


actual enum class Direction(internal val jsString: String) {
    ASCENDING("asc"),
    DESCENDING("desc")
}


actual interface FirebaseDataConverter<T> {
    actual fun fromFirestoreInstance(snapshot: QueryDocumentSnapshot<T>): T
    actual fun toFirestoreInstance(modelObject: T): Any


    fun toJsFirebaseDataConverter(): external.firebase.firestore.FirestoreDataConverter<T> = object: external.firebase.firestore.FirestoreDataConverter<T>{
        override fun fromFirestore(snapshot: external.firebase.firestore.QueryDocumentSnapshot<DocumentData>): T = fromFirestoreInstance(QueryDocumentSnapshot(snapshot))

        override fun toFirestore(modelObject: T): DocumentData = toFirestoreInstance(modelObject).unsafeCast<DocumentData>()

    }

}

actual open class DocumentSnapshot(open val js: external.firebase.firestore.DocumentSnapshot<DocumentData>) {
    actual fun get(fieldPath: String): Any? = rethrow { js.get(fieldPath) }

    internal actual inline fun <reified T> data(decoder: DocumentSnapshot.() -> T?): T? = rethrow { this.decoder() }

//    internal actual fun <T> data(decoder: (Map<String, Any>) -> T): T? = rethrow {
//        decoder(js.data().unsafeCast<Map<String, Any>>())
//    }
}

actual class QueryDocumentSnapshot<T>(override val js: external.firebase.firestore.QueryDocumentSnapshot<DocumentData>) : DocumentSnapshot(js) {}