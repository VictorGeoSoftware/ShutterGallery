package com.training.victor.development.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageViewModel (val imageDescription: String,
                           val aspectRatio: Double,
                           val category: String,
                           val imageUrl: String): Parcelable
