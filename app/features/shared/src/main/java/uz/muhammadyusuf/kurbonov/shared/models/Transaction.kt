package uz.muhammadyusuf.kurbonov.shared.models

data class Transaction(
    var id: String = "",
    var amount: Float = 0f,
    var type: TransactionType = TransactionType.INCOME,
    var note: String
)
