
package com.travelgo.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.travelgo.app.data.repository.WeatherData
import com.travelgo.app.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class WeatherUiState(
    val isLoading: Boolean = false,
    val data: WeatherData? = null,
    val error: String? = null
)

class WeatherViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = WeatherRepository(app)

    private val _state = MutableStateFlow(WeatherUiState())
    val state: StateFlow<WeatherUiState> = _state

    fun load(city: String, apiKey: String) {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = repo.fetchWeather(city, apiKey)
            _state.value = result.fold(
                onSuccess = { WeatherUiState(isLoading = false, data = it, error = null) },
                onFailure = { WeatherUiState(isLoading = false, data = null, error = it.localizedMessage ?: "Error de red") }
            )
        }
    }
}
