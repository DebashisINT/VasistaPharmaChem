package com.breezevasista.features.stockAddCurrentStock.api

import com.breezevasista.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.breezevasista.features.location.shopRevisitStatus.ShopRevisitStatusRepository

object ShopAddStockProvider {
    fun provideShopAddStockRepository(): ShopAddStockRepository {
        return ShopAddStockRepository(ShopAddStockApi.create())
    }
}