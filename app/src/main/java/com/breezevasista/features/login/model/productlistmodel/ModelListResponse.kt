package com.breezevasista.features.login.model.productlistmodel

import com.breezevasista.app.domain.ModelEntity
import com.breezevasista.app.domain.ProductListEntity
import com.breezevasista.base.BaseResponse

class ModelListResponse: BaseResponse() {
    var model_list: ArrayList<ModelEntity>? = null
}