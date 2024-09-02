package com.breezevasista.features.newcollection.model

import com.breezevasista.app.domain.CollectionDetailsEntity
import com.breezevasista.base.BaseResponse
import com.breezevasista.features.shopdetail.presentation.model.collectionlist.CollectionListDataModel

/**
 * Created by Saikat on 15-02-2019.
 */
class NewCollectionListResponseModel : BaseResponse() {
    //var collection_list: ArrayList<CollectionListDataModel>? = null
    var collection_list: ArrayList<CollectionDetailsEntity>? = null
}