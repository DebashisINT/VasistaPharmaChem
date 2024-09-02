package com.breezevasista.features.weather.api

import com.breezevasista.base.BaseResponse
import com.breezevasista.features.task.api.TaskApi
import com.breezevasista.features.task.model.AddTaskInputModel
import com.breezevasista.features.weather.model.ForeCastAPIResponse
import com.breezevasista.features.weather.model.WeatherAPIResponse
import io.reactivex.Observable

class WeatherRepo(val apiService: WeatherApi) {
    fun getCurrentWeather(zipCode: String): Observable<WeatherAPIResponse> {
        return apiService.getTodayWeather(zipCode)
    }

    fun getWeatherForecast(zipCode: String): Observable<ForeCastAPIResponse> {
        return apiService.getForecast(zipCode)
    }
}