package uz.muhammadyusuf.kurbonov.fordealer.list.di

import dagger.Subcomponent
import uz.muhammadyusuf.kurbonov.fordealer.list.ListViewModel

@ListScope
@Subcomponent
interface ListComponent {

    @ListScope
    val listViewModel: ListViewModel

    @ListScope
    @Subcomponent.Builder
    interface Builder {
        fun build(): ListComponent
    }
}