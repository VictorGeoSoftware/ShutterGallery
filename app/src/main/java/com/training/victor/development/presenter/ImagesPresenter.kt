package com.training.victor.development.presenter

import com.training.victor.development.data.DataManager
import com.training.victor.development.data.models.ImageViewModel
import com.training.victor.development.utils.getErrorMessage
import com.training.victor.development.utils.myTrace
import io.reactivex.Scheduler
import javax.inject.Inject

class ImagesPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                          private val subscriberSchedulers: Scheduler,
                                          private val dataManager: DataManager): Presenter<ImagesPresenter.ImagesView>() {

    interface ImagesView {
        fun showProgressBar(show: Boolean)
        fun onImageListReceived(imageList: List<ImageViewModel>)
        fun onImageListError(errorMessage: String)
    }


    fun getImageList(keyWord: String) {
        view?.showProgressBar(true)
        compositeDisposable.add(dataManager.getImageList(keyWord)
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe ({
                view?.showProgressBar(false)
                view?.onImageListReceived(it)
            }, {
                view?.showProgressBar(false)
                view?.onImageListError(it.getErrorMessage())
            }))

    }

    override fun destroy() {
        super.destroy()
        compositeDisposable.clear()
    }
}