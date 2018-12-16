package com.training.victor.development.data

import com.google.gson.JsonObject
import com.training.victor.development.data.models.TokenViewModel
import com.training.victor.development.network.ImagesRepository
import com.training.victor.development.network.LoginRepository
import io.reactivex.Completable
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response

class DataManager(private val imagesRepository: ImagesRepository,
                  private val loginRepository: LoginRepository,
                  private val tokenManager: TokenManager) {

    fun getProfilesList(keyWord: String): Observable<ArrayList<JsonObject>> {
        return imagesRepository.getImagesList(keyWord).flatMap {
            Observable.just(it)
        }
    }

    fun fetchGrantAccessUrl(clientID: String, redirectUri: String, responseType: String, scope: String, state: String):
            Observable<Response<ResponseBody>> {
        return loginRepository.fetchLoginWeb(clientID, redirectUri, responseType, scope, state)
    }

    fun getAccessToken(clientId: String, clientSecret: String, grantType: String, code: String): Completable {
        return Completable.fromObservable(loginRepository.getAccessToken(clientId, clientSecret, grantType, code).map {
            tokenManager.sessionToken = TokenViewModel(it.tokenType, it.accessToken)
        })
    }


}