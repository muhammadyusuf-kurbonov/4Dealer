package uz.muhammadyusuf.kurbonov.fordealer.di

import dagger.Module
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.di.AddEditComponent
import uz.muhammadyusuf.kurbonov.fordealer.list.di.ListComponent

@Module(
    subcomponents = [
        AddEditComponent::class,
        ListComponent::class
    ]
)
interface SubComponentsModule