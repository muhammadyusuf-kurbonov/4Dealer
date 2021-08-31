package uz.muhammadyusuf.kurbonov.fordealer.di

import dagger.Component
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.di.AddEditComponent
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.di.AddEditScope
import uz.muhammadyusuf.kurbonov.fordealer.list.di.ListComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [SubComponentsModule::class, FirebaseModule::class, RepositoryModule::class])
interface AppComponent {
    fun addEditComponentBuilder(): AddEditComponent.Builder
    fun listComponentBuilder(): ListComponent.Builder
}