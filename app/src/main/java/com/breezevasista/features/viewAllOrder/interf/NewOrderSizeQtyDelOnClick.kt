package com.breezevasista.features.viewAllOrder.interf

import com.breezevasista.app.domain.NewOrderGenderEntity
import com.breezevasista.features.viewAllOrder.model.ProductOrder
import java.text.FieldPosition

interface NewOrderSizeQtyDelOnClick {
    fun sizeQtySelListOnClick(product_size_qty: ArrayList<ProductOrder>)
    fun sizeQtyListOnClick(product_size_qty: ProductOrder,position: Int)
}