package com.breezevasista.features.dashboard.presentation.api.dayStartEnd

import com.breezevasista.features.stockCompetetorStock.api.AddCompStockApi
import com.breezevasista.features.stockCompetetorStock.api.AddCompStockRepository

object DayStartEndRepoProvider {
    fun dayStartRepositiry(): DayStartEndRepository {
        return DayStartEndRepository(DayStartEndApi.create())
    }

}