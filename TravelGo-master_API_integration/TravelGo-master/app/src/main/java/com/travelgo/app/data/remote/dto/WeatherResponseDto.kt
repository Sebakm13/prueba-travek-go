
package com.travelgo.app.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    @SerializedName("name") val name: String?,
    @SerializedName("weather") val weather: List<WeatherDesc> = emptyList(),
    @SerializedName("main") val main: MainInfo?
) {
    data class WeatherDesc(
        @SerializedName("description") val description: String?
    )
    data class MainInfo(
        @SerializedName("temp") val temp: Double?,
        @SerializedName("humidity") val humidity: Int?
    )
}
