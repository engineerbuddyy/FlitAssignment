package com.example.flit.flitUI.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flit.core.UiState
import com.example.flit.data.repository.DashboardRepositoryImpl
import com.example.flit.domain.model.WidgetDescriptor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    private val repository = DashboardRepositoryImpl()

    private val _state = MutableStateFlow<UiState<List<WidgetDescriptor>>>(UiState.Loading)
    val state: StateFlow<UiState<List<WidgetDescriptor>>> = _state

    init{
        loadDashboard()
    }

    private fun loadDashboard() {
        viewModelScope.launch {
            try {
                _state.value = UiState.Loading
                _state.value = UiState.Success(repository.getDashboardMetadata())
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message.toString())
            }

        }


    }

    suspend fun getBanners(id:String) = repository.getBanners(id)

}

