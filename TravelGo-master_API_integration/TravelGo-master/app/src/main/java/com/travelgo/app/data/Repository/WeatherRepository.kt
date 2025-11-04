
package com.travelgo.app.data.repository

import android.content.Context
import com.travelgo.app.data.remote.ApiService
import com.travelgo.app.data.remote.RetrofitClient
import com.travelgo.app.data.remote.dto.WeatherResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class WeatherData(
    val city: String,
    val tempC: String,
    val description: String
)

class WeatherRepository(context: Context) {
    private val api = RetrofitClient.create(context).create(ApiService::class.java)

    suspend fun fetchWeather(city: String, apiKey: String): Result<WeatherData> = withContext(Dispatchers.IO) {
        return@withContext try {
            val dto: WeatherResponseDto = api.getWeatherByCity(city, apiKey)
            val cityName = dto.name ?: city
            val temp = dto.main?.temp ?: 0.0
            val desc = dto.weather.firstOrNull()?.description ?: "Sin descripción"
            Result.success(WeatherData(cityName, "%.1f°C".format(temp), desc.replaceFirstChar { it.uppercase() }))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
