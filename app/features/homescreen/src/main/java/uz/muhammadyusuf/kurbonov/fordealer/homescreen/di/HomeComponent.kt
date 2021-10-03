package uz.muhammadyusuf.kurbonov.fordealer.homescreen.di

import dagger.Subcomponent
import uz.muhammadyusuf.kurbonov.fordealer.homescreen.HomeViewModel

@HomeScope
@Subcomponent
interface HomeComponent {
    @HomeScope
    val homeViewModel: HomeViewModel

    @HomeScope
    @Subcomponent.Builder
    interface Builder {
        fun build(): HomeComponent
    }
}