package com.training.victor.development.network.responses.models

import com.google.gson.annotations.SerializedName

data class Assets(@SerializedName("preview")
                  val preview: Preview,
                  @SerializedName("small_thumb")
                  val smallThumb: SmallThumb,
                  @SerializedName("large_thumb")
                  val largeThumb: LargeThumb,
                  @SerializedName("huge_thumb")
                  val hugeThumb: HugeThumb)