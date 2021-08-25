package uz.muhammadyusuf.kurbonov.shared.ui

interface SnackbarService {
    suspend fun showInfoMessage(information: String)
}