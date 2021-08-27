package uz.muhammadyusuf.kurbonov.shared.ui.controllers

fun interface SnackbarController {
    suspend fun showInfoMessage(information: String)
}