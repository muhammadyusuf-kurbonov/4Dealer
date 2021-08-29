package uz.muhammadyusuf.kurbonov.fordealer.add_edit.di

import dagger.Component
import dagger.Subcomponent
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.AddEditViewModel
import uz.muhammadyusuf.kurbonov.shared.datasource.Repository
import javax.inject.Singleton

@AddEditScope
@Subcomponent
interface AddEditComponent {
    @AddEditScope
    val viewModel: AddEditViewModel

    @Subcomponent.Builder
    interface Builder {
        fun build(): AddEditComponent
    }
}