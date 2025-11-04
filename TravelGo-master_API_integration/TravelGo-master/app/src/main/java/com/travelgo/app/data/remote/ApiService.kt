
package com.travelgo.app.data.remote

import com.travelgo.app.data.remote.dto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // Example: /weather?q=Santiago&appid=...&units=metric&lang=es
    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "es"
    ): WeatherResponseDto
}
