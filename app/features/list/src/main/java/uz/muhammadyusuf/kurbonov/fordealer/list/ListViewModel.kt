package uz.muhammadyusuf.kurbonov.fordealer.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.muhammadyusuf.kurbonov.fordealer.list.di.ListScope
import uz.muhammadyusuf.kurbonov.shared.datasource.Repository
import uz.muhammadyusuf.kurbonov.shared.models.Transaction
import javax.inject.Inject
@ListScope
class ListViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _allTransactions = MutableStateFlow<List<Transaction>>(emptyList())
    val allTransactions: StateFlow<List<Transaction>> = _allTransactions.asStateFlow()

    fun startListening(){
        viewModelScope.launch {
            repository.allTransactions.collect {
                _allTransactions.value = it
                Log.d("List", it.joinToString())
            }
        }
    }
}