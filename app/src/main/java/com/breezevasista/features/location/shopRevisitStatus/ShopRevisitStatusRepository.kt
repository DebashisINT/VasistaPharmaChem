package com.breezevasista.features.location.shopRevisitStatus

import com.breezevasista.base.BaseResponse
import com.breezevasista.features.location.model.ShopDurationRequest
import com.breezevasista.features.location.model.ShopRevisitStatusRequest
import io.reactivex.Observable

class ShopRevisitStatusRepository(val apiService : ShopRevisitStatusApi) {
    fun shopRevisitStatus(shopRevisitStatus: ShopRevisitStatusRequest?): Observable<BaseResponse> {
        return apiService.submShopRevisitStatus(shopRevisitStatus)
    }
}