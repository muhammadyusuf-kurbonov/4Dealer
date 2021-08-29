package uz.muhammadyusuf.kurbonov.fordealer.add_edit

sealed class AddEditState {
    object Default: AddEditState()
    object Saving: AddEditState()
    object Saved: AddEditState()
}