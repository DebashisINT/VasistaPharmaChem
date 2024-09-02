package com.breezevasista.features.photoReg.present

import com.breezevasista.app.domain.ProspectEntity
import com.breezevasista.features.photoReg.model.UserListResponseModel

interface DsStatusListner {
    fun getDSInfoOnLick(obj: ProspectEntity)
}