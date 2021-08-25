package uz.muhammadyusuf.kurbonov.shared

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("NavHost controller not initialized")
}

val LocalSnackbarService = staticCompositionLocalOf<SnackbarService> {
    error("Snackbar service not initialized")
}