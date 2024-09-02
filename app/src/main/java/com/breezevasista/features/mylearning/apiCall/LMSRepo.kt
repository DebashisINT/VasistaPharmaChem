package com.breezevasista.features.mylearning.apiCall

import com.breezevasista.base.BaseResponse
import com.breezevasista.features.login.api.opportunity.OpportunityListApi
import com.breezevasista.features.login.model.opportunitymodel.OpportunityStatusListResponseModel
import com.breezevasista.features.mylearning.LMS_CONTENT_INFO
import com.breezevasista.features.mylearning.MyCommentListResponse
import com.breezevasista.features.mylearning.MyLarningListResponse
import com.breezevasista.features.mylearning.TopicListResponse
import com.breezevasista.features.mylearning.VideoPlayLMS
import com.breezevasista.features.mylearning.VideoTopicWiseResponse
import io.reactivex.Observable

class LMSRepo(val apiService: LMSApi) {

    fun getTopics(user_id: String): Observable<TopicListResponse> {
        return apiService.getTopics(user_id)
    }

    fun getTopicsWiseVideo(user_id: String ,topic_id: String): Observable<VideoTopicWiseResponse> {
        return apiService.getTopicswiseVideoApi(user_id,topic_id)
    }

    fun saveContentInfoApi(lms_content_info: LMS_CONTENT_INFO): Observable<BaseResponse> {
        return apiService.saveContentInfoApi(lms_content_info)
    }

    fun getMyLraningInfo(user_id: String): Observable<MyLarningListResponse> {
        return apiService.getMyLearningContentList(user_id)
    }

    fun getCommentInfo(topic_id: String ,content_id: String): Observable<MyCommentListResponse> {
        return apiService.getCommentInfo(topic_id , content_id)
    }
}