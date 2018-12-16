package com.training.victor.development.network.responses

import com.google.gson.annotations.SerializedName

data class AccessTokenResp (@SerializedName("access_token") val accessToken: String,
                            @SerializedName("token_type") val tokenType: String)