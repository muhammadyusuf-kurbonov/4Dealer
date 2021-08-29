package uz.muhammadyusuf.kurbonov.fordealer.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.di.LocalTesting
import javax.inject.Singleton

@Module
class FirebaseModule {
    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @LocalTesting
    @Provides
    fun provideTestingFirestore(): FirebaseFirestore{
        val instance = FirebaseFirestore.getInstance()
        instance.useEmulator("10.0.2.2",8080)
        instance.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()

        return instance
    }
}