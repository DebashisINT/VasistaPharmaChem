package com.breezevasista.features.weather.api

import com.breezevasista.features.task.api.TaskApi
import com.breezevasista.features.task.api.TaskRepo

object WeatherRepoProvider {
    fun weatherRepoProvider(): WeatherRepo {
        return WeatherRepo(WeatherApi.create())
    }
}