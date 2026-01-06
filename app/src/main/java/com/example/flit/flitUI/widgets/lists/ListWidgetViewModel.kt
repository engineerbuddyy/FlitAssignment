package com.example.flit.flitUI.widgets.lists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flit.core.UiState
import com.example.flit.data.repository.DashboardRepositoryImpl
import com.example.flit.domain.model.ListWidgetConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

class ListWidgetViewModel(
    private val instanceId: String
) : ViewModel() {

    private val repository = DashboardRepositoryImpl()

    private val _state =
        MutableStateFlow<UiState<List<ListWidgetConfig>>>(UiState.Loading)
    val state: StateFlow<UiState<List<ListWidgetConfig>>> = _state

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                _state.value = UiState.Loading
                _state.value = UiState.Success(repository.getList(instanceId))
            } catch (e: Exception) {
                _state.value = UiState.Error("Failed to load list")
            }
        }
    }
}
