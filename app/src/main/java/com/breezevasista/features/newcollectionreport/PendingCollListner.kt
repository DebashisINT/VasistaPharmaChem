package com.breezevasista.features.newcollectionreport

import com.breezevasista.features.photoReg.model.UserListResponseModel

interface PendingCollListner {
    fun getUserInfoOnLick(obj: PendingCollData)
}