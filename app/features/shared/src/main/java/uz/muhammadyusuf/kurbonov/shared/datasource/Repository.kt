package uz.muhammadyusuf.kurbonov.shared.datasource

import uz.muhammadyusuf.kurbonov.shared.models.Transaction

interface Repository {

    companion object{
        fun getFirestoreRepository(): Repository = FirebaseRepositoryImpl()
    }

    suspend fun createTransaction(transaction: Transaction)
}