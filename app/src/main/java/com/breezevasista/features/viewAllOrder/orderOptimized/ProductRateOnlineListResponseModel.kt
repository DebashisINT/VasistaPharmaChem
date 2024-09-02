package com.breezevasista.features.viewAllOrder.orderOptimized

import com.breezevasista.app.domain.ProductOnlineRateTempEntity
import com.breezevasista.base.BaseResponse
import com.breezevasista.features.login.model.productlistmodel.ProductRateDataModel
import java.io.Serializable

class ProductRateOnlineListResponseModel: BaseResponse(), Serializable {
    var product_rate_list: ArrayList<ProductOnlineRateTempEntity>? = null
}