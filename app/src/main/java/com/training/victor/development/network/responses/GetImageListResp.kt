package com.training.victor.development.network.responses

import com.google.gson.annotations.SerializedName
import com.training.victor.development.network.responses.models.DataItem

data class GetImageListResp(@SerializedName("per_page")
                            val perPage: Int = 0,
                            @SerializedName("data")
                            val data: List<DataItem>?,
                            @SerializedName("total_count")
                            val totalCount: Int = 0,
                            @SerializedName("page")
                            val page: Int = 0,
                            @SerializedName("search_id")
                            val searchId: String = "")