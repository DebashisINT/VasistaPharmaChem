package com.breezevasista.features.stockCompetetorStock.api

import com.breezevasista.base.BaseResponse
import com.breezevasista.features.orderList.model.NewOrderListResponseModel
import com.breezevasista.features.stockCompetetorStock.ShopAddCompetetorStockRequest
import com.breezevasista.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class AddCompStockRepository(val apiService:AddCompStockApi){

    fun addCompStock(shopAddCompetetorStockRequest: ShopAddCompetetorStockRequest): Observable<BaseResponse> {
        return apiService.submShopCompStock(shopAddCompetetorStockRequest)
    }

    fun getCompStockList(sessiontoken: String, user_id: String, date: String): Observable<CompetetorStockGetData> {
        return apiService.getCompStockList(sessiontoken, user_id, date)
    }
}