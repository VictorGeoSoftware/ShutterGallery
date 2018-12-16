package com.training.victor.development.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.training.victor.development.MainApplication
import com.training.victor.development.R
import com.training.victor.development.presenter.LoginPresenter
import com.training.victor.development.ui.web.MyWebViewClient
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity: AppCompatActivity(), LoginPresenter.LoginView, MyWebViewClient.AuthenticationCodeListener {

    @Inject lateinit var loginPresenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        (application as MainApplication).createPresenterComponent().inject(this)

        webView.webViewClient = MyWebViewClient(this)
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true

        loginPresenter.view = this
        loginPresenter.showUpGrantAccessUrl()
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as MainApplication).releasePresenterComponent()
    }


    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }


    // ----------------------------------------------------------------------------
    // ------------------------------ VIEW INTERFACE ------------------------------
    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun onAccessGrantingUrlReceived(urlToShow: String) {
        webView.visibility = View.VISIBLE
        webView.loadUrl(urlToShow)
    }

    override fun onAccessTokenReady() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    // ---------------------------------------------------------------------------------------------
    // ------------------------------ AUTHENTICATION CLIENT INTERFACE ------------------------------
    override fun onAuthenticationCodeReceived(code: String) {
        webView.visibility = View.GONE
        loginPresenter.getAccessToken(code)
    }

}