package com.training.victor.development.presenter

import com.training.victor.development.BuildConfig
import com.training.victor.development.data.DataManager
import com.training.victor.development.utils.myTrace
import io.reactivex.Scheduler
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                         private val subscriberSchedulers: Scheduler,
                                         private val dataManager: DataManager): Presenter<LoginPresenter.LoginView>() {

    interface LoginView {
        fun showProgressBar(show: Boolean)
        fun onAccessGrantingUrlReceived(urlToShow: String)
        fun onAccessTokenReady()
    }


    fun showUpGrantAccessUrl() {
        view?.showProgressBar(true)
        val responseType = "code"
        val state = "OK"

        compositeDisposable.add(dataManager.fetchGrantAccessUrl(BuildConfig.CONSUMER_KEY, BuildConfig.REDIRECT_URI,
            responseType, BuildConfig.APP_PERMISSION_SCOPE, state)
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                view?.showProgressBar(false)
                view?.onAccessGrantingUrlReceived(it.raw().request().url().toString())
            }, {
                view?.showProgressBar(false)
            }))
    }

    fun getAccessToken(code: String) {
        view?.showProgressBar(true)
        val grantType = "authorization_code"

        compositeDisposable.add(dataManager.getAccessToken(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_SECRET,
            grantType, code)
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                view?.showProgressBar(false)
                view?.onAccessTokenReady()
            }, {
                view?.showProgressBar(false)
            }))
    }
}