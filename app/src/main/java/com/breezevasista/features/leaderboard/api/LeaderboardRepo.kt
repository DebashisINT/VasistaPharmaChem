package com.breezevasista.features.leaderboard.api

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import com.fasterxml.jackson.databind.ObjectMapper
import com.breezevasista.app.FileUtils
import com.breezevasista.app.Pref
import com.breezevasista.base.BaseResponse
import com.breezevasista.features.addshop.model.AddLogReqData
import com.breezevasista.features.addshop.model.AddShopRequestData
import com.breezevasista.features.addshop.model.AddShopResponse
import com.breezevasista.features.addshop.model.LogFileResponse
import com.breezevasista.features.addshop.model.UpdateAddrReq
import com.breezevasista.features.contacts.CallHisDtls
import com.breezevasista.features.contacts.CompanyReqData
import com.breezevasista.features.contacts.ContactMasterRes
import com.breezevasista.features.contacts.SourceMasterRes
import com.breezevasista.features.contacts.StageMasterRes
import com.breezevasista.features.contacts.StatusMasterRes
import com.breezevasista.features.contacts.TypeMasterRes
import com.breezevasista.features.dashboard.presentation.DashboardActivity
import com.breezevasista.features.login.model.WhatsappApiData
import com.breezevasista.features.login.model.WhatsappApiFetchData
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by Puja on 10-10-2024.
 */
class LeaderboardRepo(val apiService: LeaderboardApi) {

    fun branchlist(session_token: String): Observable<LeaderboardBranchData> {
        return apiService.branchList(session_token)
    }
    fun ownDatalist(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOwnData> {
        return apiService.ownDatalist(user_id,activitybased,branchwise,flag)
    }
    fun overAllAPI(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOverAllData> {
        return apiService.overAllDatalist(user_id,activitybased,branchwise,flag)
    }
}