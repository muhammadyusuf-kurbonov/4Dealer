package uz.muhammadyusuf.kurbonov.fordealer.di

import dagger.Module
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.di.AddEditComponent

@Module(
    subcomponents = [AddEditComponent::class]
)
interface SubComponentsModule