package uz.muhammadyusuf.kurbonov.fordealer.add_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.di.LocalTesting
import uz.muhammadyusuf.kurbonov.shared.datasource.Repository
import uz.muhammadyusuf.kurbonov.shared.models.Transaction
import javax.inject.Inject


class AddEditViewModel @Inject constructor(
    @LocalTesting private val repository: Repository
): ViewModel() {
    private val _addEditState = MutableStateFlow<AddEditState>(AddEditState.Default)
    val addEditState: StateFlow<AddEditState> = _addEditState.asStateFlow()

    fun save(transaction: Transaction){
        viewModelScope.launch {
            _addEditState.tryEmit(AddEditState.Saving)
            repository.createTransaction(transaction)
            _addEditState.tryEmit(AddEditState.Saved)
        }
    }
}