package com.breezevasista.features.viewAllOrder.interf

import com.breezevasista.app.domain.NewOrderGenderEntity
import com.breezevasista.features.viewAllOrder.model.ProductOrder

interface ColorListOnCLick {
    fun colorListOnCLick(size_qty_list: ArrayList<ProductOrder>, adpPosition:Int)
}