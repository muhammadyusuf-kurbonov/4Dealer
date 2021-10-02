package uz.muhammadyusuf.kurbonov.fordealer.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.muhammadyusuf.kurbonov.fordealer.homescreen.di.HomeScope
import uz.muhammadyusuf.kurbonov.shared.datasource.Repository
import javax.inject.Inject

@HomeScope
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _balance = MutableStateFlow(0.0)
    val balance = _balance.asStateFlow()

    fun startListening(){
        viewModelScope.launch {
            repository.balance.collect {
                _balance.value = it
            }
        }
    }
}