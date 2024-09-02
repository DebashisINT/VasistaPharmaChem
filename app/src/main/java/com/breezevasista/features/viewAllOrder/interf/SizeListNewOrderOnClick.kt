package com.breezevasista.features.viewAllOrder.interf

import com.breezevasista.app.domain.NewOrderProductEntity
import com.breezevasista.app.domain.NewOrderSizeEntity

interface SizeListNewOrderOnClick {
    fun sizeListOnClick(size: NewOrderSizeEntity)
}