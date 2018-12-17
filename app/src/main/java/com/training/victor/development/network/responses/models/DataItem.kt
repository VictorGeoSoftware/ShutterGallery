package com.training.victor.development.network.responses.models

import com.google.gson.annotations.SerializedName

data class DataItem(@SerializedName("assets")
                    val assets: Assets,
                    @SerializedName("contributor")
                    val contributor: Contributor,
                    @SerializedName("media_type")
                    val mediaType: String = "",
                    @SerializedName("aspect")
                    val aspect: Double = 0.0,
                    @SerializedName("description")
                    val description: String = "",
                    @SerializedName("has_model_release")
                    val hasModelRelease: Boolean = false,
                    @SerializedName("id")
                    val id: String = "",
                    @SerializedName("image_type")
                    val imageType: String = "")