package com.breezevasista.features.stockAddCurrentStock.api

import com.breezevasista.base.BaseResponse
import com.breezevasista.features.location.model.ShopRevisitStatusRequest
import com.breezevasista.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.breezevasista.features.stockAddCurrentStock.ShopAddCurrentStockRequest
import com.breezevasista.features.stockAddCurrentStock.model.CurrentStockGetData
import com.breezevasista.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class ShopAddStockRepository (val apiService : ShopAddStockApi){
    fun shopAddStock(shopAddCurrentStockRequest: ShopAddCurrentStockRequest?): Observable<BaseResponse> {
        return apiService.submShopAddStock(shopAddCurrentStockRequest)
    }

    fun getCurrStockList(sessiontoken: String, user_id: String, date: String): Observable<CurrentStockGetData> {
        return apiService.getCurrStockListApi(sessiontoken, user_id, date)
    }

}