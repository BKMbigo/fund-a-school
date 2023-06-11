@file:JsModule("firebase/firestore")
@file:JsNonModule

package external.firebase.firestore

import kotlin.js.Promise

external class Firestore {
    val app: external.firebase.app.FirebaseApp
}

external fun getFirestore(app: external.firebase.app.FirebaseApp): Firestore
external fun collection(firestore: Firestore, path: String): CollectionReference<DocumentData>
external fun <T: DocumentData> addDoc(reference: CollectionReference<T>, data: Any): Promise<DocumentReference<T>>
external fun <T: DocumentData> doc(reference: CollectionReference<T>, path: String): DocumentReference<T>
external fun <T: DocumentData> deleteDoc(reference: DocumentReference<T>): Promise<Unit>
external fun <T: DocumentData> getDoc(reference: DocumentReference<T>): Promise<DocumentSnapshot<T>>
external fun <T: DocumentData> doc(reference: DocumentReference<T>, path: String): Promise<DocumentReference<T>>
external fun <T: DocumentData> setDoc(reference: DocumentReference<T>, data: Any): Promise<Unit>
external fun <T: DocumentData> updateDoc(reference: DocumentReference<T>, data: Map<String, Any?>): Promise<Unit>
external fun <T: DocumentData> getDocs(query: Query<T>): Promise<QuerySnapshot<T>>
external fun <T: DocumentData> onSnapshot(query: Query<T>, onNext: (snapshot: QuerySnapshot<T>) -> Unit, onError: (error: FirestoreError) -> Unit) : () -> Unit
external fun orderBy(fieldPath: String, directionStr: String) : QueryOrderByConstraint
external fun where(fieldPath: String, opStr: String, value: Any) : QueryFieldFilterConstraint
external fun endAt(vararg fieldValues: Any): QueryEndAtConstraint
external fun endBefore(vararg fieldValues: Any): QueryEndAtConstraint
external fun startAt(vararg fieldValues: Any): QueryStartAtConstraint
external fun startAfter(vararg fieldValues: Any): QueryStartAtConstraint
external fun <T: DocumentData> query(query: Query<T>, vararg queryConstraints: QueryConstraint): Query<T>


external class CollectionReference<T: DocumentData>: Query<T> {
    val id: String
    val parent: DocumentReference<DocumentData>?
    val path: String

    override fun withConverter(converter: FirestoreDataConverter<T>?): CollectionReference<T>
}

external fun doc(firestore: Firestore, path: String): DocumentReference<DocumentData>
external class DocumentReference<T: DocumentData> {
    val converter: FirestoreDataConverter<T>?
    val firestore: Firestore
    val id: String
    val parent: CollectionReference<T>
    val path: String

    fun withConverter(converter: FirestoreDataConverter<T>?): CollectionReference<T>
}

external interface FirestoreDataConverter<T> {
    fun fromFirestore(snapshot: QueryDocumentSnapshot<DocumentData>): T
    fun toFirestore(modelObject: T): DocumentData
}

external interface DocumentData

open external class Query<T: DocumentData> {
    val converter: FirestoreDataConverter<T>?
    val firestore: Firestore

    open fun withConverter(converter: FirestoreDataConverter<T>?): CollectionReference<T>
}
external class QuerySnapshot<T: DocumentData> {
    val docs: Array<QueryDocumentSnapshot<T>>
    val empty: Boolean
    val query: Query<T>
    val size: Int

    // fun docChanges(): Array<DocumentChanges<T>>
    // fun forEach(callback: (result: (QueryDocumentSnapshot<T>) -> Unit)
}
abstract external class QueryConstraint {
    // val type: QueryConstraintType
}
external class QueryStartAtConstraint: QueryConstraint {
    // val type: QueryConstraintType
}
external class QueryEndAtConstraint: QueryConstraint {
    // val type: QueryConstraintType
}
external class QueryOrderByConstraint: QueryConstraint {
    // val type: QueryConstraintType
}
external class QueryFieldFilterConstraint: QueryConstraint {
    // val type: QueryConstraintType
}
external class QueryCompositeFilterConstraint: QueryConstraint {
    // val type: QueryConstraintType
}

external class QueryDocumentSnapshot<T: DocumentData>: DocumentSnapshot<T> {
    override fun data(): T
}

open external class DocumentSnapshot<T: DocumentData> {
    fun constructor()
    val id: String
    val ref: DocumentReference<T>

    open fun data(): T?
    fun exists(): Boolean
    fun get(fieldPath: String): Any?

}

external class FirestoreError {
    var code: String
    var message: String
}