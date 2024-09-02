package com.breezevasista.features.alarm.api.performance_report_list_api

import com.breezevasista.app.Pref
import com.breezevasista.features.alarm.model.PerformanceReportResponseModel
import io.reactivex.Observable

/**
 * Created by Saikat on 21-02-2019.
 */
class PerformanceReportRepo(val apiService: PerformanceReportApi) {
    fun getPerformanceReportList(from_date: String, to_date: String): Observable<PerformanceReportResponseModel> {
        return apiService.performanceReportResponse(Pref.session_token!!, Pref.user_id!!, from_date, to_date)
    }
}