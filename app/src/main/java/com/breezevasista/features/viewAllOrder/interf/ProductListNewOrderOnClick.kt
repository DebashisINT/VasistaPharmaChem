package com.breezevasista.features.viewAllOrder.interf

import com.breezevasista.app.domain.NewOrderGenderEntity
import com.breezevasista.app.domain.NewOrderProductEntity

interface ProductListNewOrderOnClick {
    fun productListOnClick(product: NewOrderProductEntity)
}