package com.breezevasista.features.shopdetail.presentation.api

import com.breezevasista.app.NetworkConstant
import com.breezevasista.base.BaseResponse
import com.breezevasista.features.addshop.model.AddShopRequestData
import com.breezevasista.features.addshop.model.AddShopResponse
import com.breezevasista.features.addshop.model.LogFileResponse
import com.breezevasista.features.addshop.model.UpdateAddrReq
import com.breezevasista.features.addshop.model.UpdateAddressShop
import com.breezevasista.features.contacts.AutoMailDtls
import com.breezevasista.features.contacts.CallHisDtls
import com.breezevasista.features.contacts.CompanyReqData
import com.breezevasista.features.contacts.ContactMasterRes
import com.breezevasista.features.contacts.SourceMasterRes
import com.breezevasista.features.contacts.StageMasterRes
import com.breezevasista.features.contacts.StatusMasterRes
import com.breezevasista.features.contacts.TypeMasterRes
import com.breezevasista.features.login.model.WhatsappApiData
import com.breezevasista.features.login.model.WhatsappApiFetchData
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Created by Saikat on 10-10-2018.
 */
interface EditShopApi {

    @POST("Shoplist/EditShop")
    fun editShop(@Body addShop: AddShopRequestData?): Observable<AddShopResponse>

    @POST("WhatsAppMessageInfo/WhatsAppMsgSave")
    fun whatsAppStatusSyncApi(@Body addShop: WhatsappApiData?): Observable<BaseResponse>

    @FormUrlEncoded
    @POST("WhatsAppMessageInfo/WhatsAppMsgList")
    fun whatsAppStatusFetchApi(@Field("user_id") user_id: String): Observable<WhatsappApiFetchData>

    @Multipart
    @POST("ShopRegistration/NewShopEdit")
    fun editShopWithDegImage(@Query("data") addShop: String, @Part deg_img_data: MultipartBody.Part?): Observable<AddShopResponse>


    @Multipart
    @POST("ShopRegistration/EditShop")
    fun editShopWithImage(@Query("data") addShop: String, @Part logo_img_data: MultipartBody.Part?): Observable<AddShopResponse>

    @Multipart
    @POST("APPLogFilesDetection/APPLogFilesSave")
    fun logshareFile(@Query("data") userId: String, @Part attachments: MultipartBody.Part?): Observable<LogFileResponse>

    @FormUrlEncoded
    @POST("CRMContactInfo/CRMCompanyList")
    fun callCompanyMasterApi(@Field("session_token") session_token: String): Observable<ContactMasterRes>

    @FormUrlEncoded
    @POST("CRMContactInfo/CRMCompanySave")
    fun saveCompanyMasterApi(@Field("session_token") session_token: String,@Field("created_by") created_by: String,@Field("company_name") company_name: String): Observable<BaseResponse>

    @POST("CRMContactInfo/CRMCompanySave")
    fun saveCompanyMasterApiNw(@Body addShop: CompanyReqData?): Observable<BaseResponse>

    @FormUrlEncoded
    @POST("CRMContactInfo/CRMTypeList")
    fun callTypeMasterApi(@Field("session_token") session_token: String): Observable<TypeMasterRes>


    @FormUrlEncoded
    @POST("CRMContactInfo/CRMStatusList")
    fun callStatusMasterApi(@Field("session_token") session_token: String): Observable<StatusMasterRes>


    @FormUrlEncoded
    @POST("CRMContactInfo/CRMSourceList")
    fun callSourceMasterApi(@Field("session_token") session_token: String): Observable<SourceMasterRes>

    @FormUrlEncoded
    @POST("CRMContactInfo/CRMStageList")
    fun callStageMasterApi(@Field("session_token") session_token: String): Observable<StageMasterRes>


    @POST("CallLogInformations/CallLogListSave")
    fun callLogListSaveApi(@Body callLogHisSave: CallHisDtls?): Observable<BaseResponse>

    @POST("Shoplist/ITCShopAddressEdit")
    fun callUpdateAddressSaveApi(@Body updateAddrReq: UpdateAddrReq?): Observable<BaseResponse>

    @FormUrlEncoded
    @POST("CallLogInformations/CallLogList")
    fun callCallListHisAPI(@Field("user_id") user_id: String): Observable<CallHisDtls>

    @FormUrlEncoded
    @POST("SendAutoMail/SendAutoMailInfo")
    fun autoMailDtlsAPI(@Field("user_id") user_id: String): Observable<AutoMailDtls>

    /**
     * Companion object to create the GithubApiService
     */
    companion object Factory {
        fun create(): EditShopApi {
            val retrofit = Retrofit.Builder()
                    .client(NetworkConstant.setTimeOut())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(NetworkConstant.ADD_SHOP_BASE_URL)
                    .build()

            return retrofit.create(EditShopApi::class.java)
        }

        fun createWithoutMultipart(): EditShopApi {
            val retrofit = Retrofit.Builder()
                    .client(NetworkConstant.setTimeOut())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(NetworkConstant.BASE_URL)
                    .build()

            return retrofit.create(EditShopApi::class.java)
        }
    }
}