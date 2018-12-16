package com.training.victor.development.ui.web

import android.graphics.Bitmap
import android.net.Uri
import android.webkit.*
import com.training.victor.development.BuildConfig
import com.training.victor.development.utils.myTrace

class MyWebViewClient(private val authenticationCodeListener: AuthenticationCodeListener): WebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        if (url?.contains(BuildConfig.REDIRECT_URI)!!) {
            val uri = Uri.parse(url)

            uri.getQueryParameter("code").let {
                authenticationCodeListener.onAuthenticationCodeReceived(it!!)
            }
        }
    }


    interface AuthenticationCodeListener {
        fun onAuthenticationCodeReceived(code: String)
    }
}