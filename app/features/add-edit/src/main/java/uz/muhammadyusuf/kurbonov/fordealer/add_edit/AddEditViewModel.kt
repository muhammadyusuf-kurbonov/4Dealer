package uz.muhammadyusuf.kurbonov.fordealer.add_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.di.AddEditScope
import uz.muhammadyusuf.kurbonov.shared.datasource.Repository
import uz.muhammadyusuf.kurbonov.shared.models.Transaction
import javax.inject.Inject
import javax.inject.Singleton

@AddEditScope
class AddEditViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _addEditState = MutableStateFlow<AddEditState>(AddEditState.Default)
    val addEditState: StateFlow<AddEditState> = _addEditState.asStateFlow()

    fun save(transaction: Transaction){
        viewModelScope.launch {
            _addEditState.value = AddEditState.Saving
            repository.createTransaction(transaction)
            _addEditState.value = AddEditState.Saved
        }
    }
}