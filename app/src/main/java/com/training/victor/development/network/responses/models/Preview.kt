package com.training.victor.development.network.responses.models

import com.google.gson.annotations.SerializedName

data class Preview(@SerializedName("width")
                   val width: Int = 0,
                   @SerializedName("url")
                   val url: String = "",
                   @SerializedName("height")
                   val height: Int = 0)