package uz.muhammadyusuf.kurbonov.shared.models

data class Transaction(
    var id: String = "",
    var amount: Double = 0.0,
    var note: String = "",
    var dateTime: Long = System.currentTimeMillis()
)
