package uz.muhammadyusuf.kurbonov.shared.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.getField
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.runBlocking
import uz.muhammadyusuf.kurbonov.shared.models.General
import uz.muhammadyusuf.kurbonov.shared.models.Transaction
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@ExperimentalCoroutinesApi
internal class FirebaseRepositoryImpl(private val firestore: FirebaseFirestore) : Repository {
    override suspend fun createTransaction(transaction: Transaction) = suspendCoroutine<Unit> { cont ->
        firestore.runTransaction { dbTransaction ->
            runBlocking {
                ensureActive()
                val generalDocumentReference = firestore.document("general/general")
                var generalDocument = dbTransaction.get(generalDocumentReference).toObject(General::class.java)

                val document = firestore.collection("transactions").document()
                transaction.id = document.id
                dbTransaction.set(document, transaction)

                if (generalDocument == null) generalDocument = General(0.0)
                generalDocument.balance += transaction.amount
                ensureActive()
                dbTransaction.set(generalDocumentReference, generalDocument)
            }
        }.addOnSuccessListener {
            cont.resume(Unit)
        }.addOnFailureListener {
            it.printStackTrace()
            cont.resumeWithException(it)
        }
    }

    override suspend fun deleteTransaction(transaction: Transaction) = suspendCoroutine<Unit> { cont ->
        firestore.runTransaction { dbTransaction ->
            runBlocking {
                ensureActive()
                val generalDocumentReference = firestore.document("general/general")
                var generalDocument = dbTransaction.get(generalDocumentReference).toObject(General::class.java)

                val document = firestore
                    .collection("transactions")
                    .document(transaction.id)
                dbTransaction.delete(document)

                if (generalDocument == null) generalDocument = General(0.0)
                generalDocument.balance -= transaction.amount
                ensureActive()
                dbTransaction.set(generalDocumentReference, generalDocument)
            }
        }.addOnSuccessListener {
            cont.resume(Unit)
        }.addOnFailureListener {
            it.printStackTrace()
            cont.resumeWithException(it)
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

    override val balance = callbackFlow {
        val addSnapshotListener = firestore.document("general/general")
            .addSnapshotListener { value, error ->
                if (value != null) {
                    val balance: Double = value.getField("balance")
                        ?: throw IllegalArgumentException()
                    trySendBlocking(balance)
                    Log.d("ForDealer", "balance fetched $balance")
                }
                if (error != null) throw error
            }
        awaitClose { addSnapshotListener.remove() }
    }
}