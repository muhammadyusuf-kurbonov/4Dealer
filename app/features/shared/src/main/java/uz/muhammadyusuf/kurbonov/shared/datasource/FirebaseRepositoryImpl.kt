package uz.muhammadyusuf.kurbonov.shared.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.muhammadyusuf.kurbonov.shared.models.Transaction
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@ExperimentalCoroutinesApi
internal class FirebaseRepositoryImpl(private val firestore: FirebaseFirestore) : Repository {
    override suspend fun createTransaction(transaction: Transaction) =
        suspendCoroutine<Unit> { continuation ->
            val document = firestore.collection("transactions").document()
            transaction.id = document.id
            document.set(transaction).addOnSuccessListener {
                continuation.resume(Unit)
            }.addOnFailureListener {
                continuation.resumeWithException(it)
            }
        }

    override val allTransactions: Flow<List<Transaction>> = callbackFlow {
        val listener =
            firestore.collection("transactions")
                .orderBy("dateTime", Query.Direction.DESCENDING)
                .addSnapshotListener { value, error ->
                    if (value != null)
                        trySend(value.toObjects(Transaction::class.java))
                    if (error != null) throw error
                }
        awaitClose { listener.remove() }
    }
}