package uz.muhammadyusuf.kurbonov.shared.datasource

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import uz.muhammadyusuf.kurbonov.shared.models.Transaction

interface Repository {

    companion object {
        fun getFirestoreRepository(firestore: FirebaseFirestore): Repository =
            FirebaseRepositoryImpl(firestore)
    }

    suspend fun createTransaction(transaction: Transaction)

    val allTransactions: Flow<List<Transaction>>

    val balance: Flow<Double>
}