package com.breezevasista.features.viewAllOrder.interf

import com.breezevasista.app.domain.NewOrderColorEntity
import com.breezevasista.app.domain.NewOrderProductEntity

interface ColorListNewOrderOnClick {
    fun productListOnClick(color: NewOrderColorEntity)
}