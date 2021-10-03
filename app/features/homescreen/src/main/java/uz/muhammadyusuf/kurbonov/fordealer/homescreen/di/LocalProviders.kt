package uz.muhammadyusuf.kurbonov.fordealer.homescreen.di

import androidx.compose.runtime.staticCompositionLocalOf

val LocalHomeComponent = staticCompositionLocalOf<HomeComponent> {
    error("Home component not provided")
}