package com.breezevasista.features.location.api

import com.breezevasista.features.location.shopdurationapi.ShopDurationApi
import com.breezevasista.features.location.shopdurationapi.ShopDurationRepository


object LocationRepoProvider {
    fun provideLocationRepository(): LocationRepo {
        return LocationRepo(LocationApi.create())
    }
}