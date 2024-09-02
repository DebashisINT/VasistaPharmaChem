package com.breezevasista.features.viewAllOrder.model

import com.breezevasista.app.domain.NewOrderColorEntity
import com.breezevasista.app.domain.NewOrderGenderEntity
import com.breezevasista.app.domain.NewOrderProductEntity
import com.breezevasista.app.domain.NewOrderSizeEntity
import com.breezevasista.features.stockCompetetorStock.model.CompetetorStockGetDataDtls

class NewOrderDataModel {
    var status:String ? = null
    var message:String ? = null
    var Gender_list :ArrayList<NewOrderGenderEntity>? = null
    var Product_list :ArrayList<NewOrderProductEntity>? = null
    var Color_list :ArrayList<NewOrderColorEntity>? = null
    var size_list :ArrayList<NewOrderSizeEntity>? = null
}

