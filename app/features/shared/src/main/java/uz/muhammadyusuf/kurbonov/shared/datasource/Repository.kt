package uz.muhammadyusuf.kurbonov.shared.datasource

import com.google.firebase.firestore.FirebaseFirestore
import uz.muhammadyusuf.kurbonov.shared.models.Transaction

interface Repository {

    companion object {
        fun getFirestoreRepository(firestore: FirebaseFirestore): Repository =
            FirebaseRepositoryImpl(firestore)
    }

    suspend fun createTransaction(transaction: Transaction)
}