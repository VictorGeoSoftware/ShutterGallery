package com.training.victor.development.utils

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.training.victor.development.network.responses.RequestErrorViewModel
import retrofit2.HttpException

fun ViewGroup.inflate(layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun Context.getDpFromValue(value: Int): Int =
    Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), this.resources.displayMetrics))

fun Any.myTrace(message: String) {
    System.out.println("victor - ${this.javaClass.name} - $message")
}

fun Throwable.getErrorMessage(): String {
    val defaultMessage = this.message ?: this.localizedMessage

    return if (this is HttpException) {
        val responseBody = this.response().errorBody()
        responseBody?.string()?.let {
            Gson().fromJson<RequestErrorViewModel>(it, RequestErrorViewModel::class.java).errorDescription
        }.run {
            defaultMessage
        }
    } else {
        defaultMessage
    }
}