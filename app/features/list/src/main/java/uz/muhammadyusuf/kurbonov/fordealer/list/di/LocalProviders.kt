package uz.muhammadyusuf.kurbonov.fordealer.list.di

import androidx.compose.runtime.staticCompositionLocalOf

val LocalListComponent = staticCompositionLocalOf<ListComponent> {
    error("List component not provided")
}