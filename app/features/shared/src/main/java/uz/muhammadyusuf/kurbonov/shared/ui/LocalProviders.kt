package uz.muhammadyusuf.kurbonov.shared.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import uz.muhammadyusuf.kurbonov.shared.ui.controllers.SnackbarController
import uz.muhammadyusuf.kurbonov.shared.ui.controllers.TitleController

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("NavHost controller not initialized")
}

val LocalSnackbarController = staticCompositionLocalOf<SnackbarController> {
    error("Snackbar service not initialized")
}

val LocalTitleController = staticCompositionLocalOf<TitleController> {
    error(("Title controller not initialized"))
}