package com.breezevasista.features.mylearning.apiCall

import com.breezevasista.features.login.api.opportunity.OpportunityListApi
import com.breezevasista.features.login.api.opportunity.OpportunityListRepo

object LMSRepoProvider {
    fun getTopicList(): LMSRepo {
        return LMSRepo(LMSApi.create())
    }
}