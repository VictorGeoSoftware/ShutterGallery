package com.training.victor.development.data

import com.training.victor.development.data.models.ImageViewModel
import com.training.victor.development.network.ImagesRepository
import com.training.victor.development.network.LoginRepository
import io.reactivex.Completable
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response

class DataManager(private val imagesRepository: ImagesRepository,
                  private val loginRepository: LoginRepository,
                  private val tokenManager: TokenManager) {

    @Deprecated("Not necessary anymore")
    fun fetchGrantAccessUrl(clientID: String, redirectUri: String, responseType: String, scope: String, state: String):
            Observable<Response<ResponseBody>> {
        return loginRepository.fetchLoginWeb(clientID, redirectUri, responseType, scope, state)
    }

    @Deprecated("Not necessary anymore")
    fun getAccessToken(clientId: String, clientSecret: String, grantType: String, code: String): Completable {
        return Completable.fromObservable(loginRepository.getAccessToken(clientId, clientSecret, grantType, code))
    }

    fun getImageList(keyWord: String): Observable<List<ImageViewModel>> {
        return imagesRepository.getImagesList(keyWord).flatMap {
            Observable.just(it.data?.map { dataItem ->
                ImageViewModel(dataItem.description, dataItem.aspect, dataItem.assets.hugeThumb.url)
            })
        }
    }
}