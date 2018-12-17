package com.training.victor.development.network

import com.training.victor.development.network.responses.GetImageListResp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesRepository {

    @GET("/v2/images/search")
    fun getImagesList(@Query("query") keyWord: String): Observable<GetImageListResp>
}