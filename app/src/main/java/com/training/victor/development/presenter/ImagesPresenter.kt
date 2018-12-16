package com.training.victor.development.presenter

import com.google.gson.JsonObject
import com.training.victor.development.data.DataManager
import com.training.victor.development.utils.getErrorMessage
import com.training.victor.development.utils.myTrace
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ImagesPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                          private val subscriberSchedulers: Scheduler,
                                          private val dataManager: DataManager): Presenter<ImagesPresenter.ImagesView>() {

    interface ImagesView {
        fun showProgressBar(show: Boolean)
        fun onImageListReceived(profilesList: ArrayList<JsonObject>)
        fun onImageListError()
    }


    fun getImageList(keyWord: String) {
        view?.showProgressBar(true)
        compositeDisposable.add(dataManager.getProfilesList(keyWord)
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe ({
                view?.showProgressBar(false)
                view?.onImageListReceived(it)
            }, {
                view?.showProgressBar(false)
                myTrace("getImageList error :: ${it.getErrorMessage()}")
                view?.onImageListError()
            }))

    }

    override fun destroy() {
        super.destroy()
        compositeDisposable.clear()
    }
}