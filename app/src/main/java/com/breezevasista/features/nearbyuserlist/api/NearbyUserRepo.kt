package com.breezevasista.features.nearbyuserlist.api

import com.breezevasista.app.Pref
import com.breezevasista.features.nearbyuserlist.model.NearbyUserResponseModel
import com.breezevasista.features.newcollection.model.NewCollectionListResponseModel
import com.breezevasista.features.newcollection.newcollectionlistapi.NewCollectionListApi
import io.reactivex.Observable

class NearbyUserRepo(val apiService: NearbyUserApi) {
    fun nearbyUserList(): Observable<NearbyUserResponseModel> {
        return apiService.getNearbyUserList(Pref.session_token!!, Pref.user_id!!)
    }
}