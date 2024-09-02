package com.breezevasista.features.viewAllOrder.api.addorder

import com.breezevasista.features.timesheet.api.TimeSheetApi
import com.breezevasista.features.timesheet.api.TimeSheetRepo

/**
 * Created by Saikat on 01-10-2018.
 */
object AddOrderRepoProvider {
    fun provideAddOrderRepository(): AddOrderRepo {
        return AddOrderRepo(AddOrderApi.create())
    }

    fun provideAddOrderImageRepository(): AddOrderRepo {
        return AddOrderRepo(AddOrderApi.createImage())
    }
}