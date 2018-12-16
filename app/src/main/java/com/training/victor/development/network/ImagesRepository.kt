package com.training.victor.development.network

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesRepository {

    @GET("/v2/images/search")
    fun getImagesList(@Query("query") keyWord: String): Observable<ArrayList<JsonObject>>
}