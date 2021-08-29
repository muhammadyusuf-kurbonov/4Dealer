package uz.muhammadyusuf.kurbonov.shared.datasource

import com.google.firebase.firestore.FirebaseFirestore
import uz.muhammadyusuf.kurbonov.shared.models.Transaction
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

}