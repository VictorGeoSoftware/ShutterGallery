package com.training.victor.development.network

import com.training.victor.development.network.responses.AccessTokenResp
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface LoginRepository {

    @GET("/v2/oauth/authorize")
    fun fetchLoginWeb(@Query("client_id") clientId: String,
                      @Query("redirect_uri") redirectUri: String,
                      @Query("response_type") responseType: String,
                      @Query("scope") scope: String,
                      @Query("state") state: String): Observable<Response<ResponseBody>>

    @FormUrlEncoded
    @POST("/v2/oauth/access_token")
    fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String,
        @Field("code") code: String): Observable<AccessTokenResp>
}