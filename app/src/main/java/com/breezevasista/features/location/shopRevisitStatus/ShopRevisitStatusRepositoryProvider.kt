package com.breezevasista.features.location.shopRevisitStatus

import com.breezevasista.features.location.shopdurationapi.ShopDurationApi
import com.breezevasista.features.location.shopdurationapi.ShopDurationRepository

object ShopRevisitStatusRepositoryProvider {
    fun provideShopRevisitStatusRepository(): ShopRevisitStatusRepository {
        return ShopRevisitStatusRepository(ShopRevisitStatusApi.create())
    }
}