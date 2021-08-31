package uz.muhammadyusuf.kurbonov.fordealer.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.di.LocalTesting
import uz.muhammadyusuf.kurbonov.shared.datasource.Repository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(@LocalTesting firestore: FirebaseFirestore): Repository {
        return Repository.getFirestoreRepository(firestore)
    }
}