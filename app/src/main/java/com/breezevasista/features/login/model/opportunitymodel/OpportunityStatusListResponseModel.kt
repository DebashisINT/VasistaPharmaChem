package com.breezevasista.features.login.model.opportunitymodel

import com.breezevasista.app.domain.OpportunityStatusEntity
import com.breezevasista.app.domain.ProductListEntity
import com.breezevasista.base.BaseResponse

/**
 * Created by Puja on 30.05.2024
 */
class OpportunityStatusListResponseModel : BaseResponse() {
    var status_list: ArrayList<OpportunityStatusEntity>? = null
}