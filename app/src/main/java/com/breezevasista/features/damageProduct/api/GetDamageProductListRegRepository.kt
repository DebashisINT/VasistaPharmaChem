package com.breezevasista.features.damageProduct.api

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import com.breezevasista.app.FileUtils
import com.breezevasista.base.BaseResponse
import com.breezevasista.features.NewQuotation.model.*
import com.breezevasista.features.addshop.model.AddShopRequestData
import com.breezevasista.features.addshop.model.AddShopResponse
import com.breezevasista.features.damageProduct.model.DamageProductResponseModel
import com.breezevasista.features.damageProduct.model.delBreakageReq
import com.breezevasista.features.damageProduct.model.viewAllBreakageReq
import com.breezevasista.features.login.model.userconfig.UserConfigResponseModel
import com.breezevasista.features.myjobs.model.WIPImageSubmit
import com.breezevasista.features.photoReg.model.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class GetDamageProductListRegRepository(val apiService : GetDamageProductListApi) {

    fun viewBreakage(req: viewAllBreakageReq): Observable<DamageProductResponseModel> {
        return apiService.viewBreakage(req)
    }

    fun delBreakage(req: delBreakageReq): Observable<BaseResponse>{
        return apiService.BreakageDel(req.user_id!!,req.breakage_number!!,req.session_token!!)
    }

}