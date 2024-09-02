package com.breezevasista.features.lead.api

import com.breezevasista.features.NewQuotation.api.GetQuotListRegRepository
import com.breezevasista.features.NewQuotation.api.GetQutoListApi


object GetLeadRegProvider {
    fun provideList(): GetLeadListRegRepository {
        return GetLeadListRegRepository(GetLeadListApi.create())
    }
}