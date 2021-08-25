package uz.muhammadyusuf.kurbonov.shared

interface SnackbarService {
    suspend fun showInfoMessage(information: String)
}