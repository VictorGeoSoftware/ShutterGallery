package com.training.victor.development.di.modules

import com.training.victor.development.data.DataManager
import com.training.victor.development.data.TokenManager
import com.training.victor.development.network.ImagesRepository
import com.training.victor.development.network.LoginRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataManagerModule {
    @Provides
    @Singleton
    fun provideDataManager(profileRepository: ImagesRepository, loginRepository: LoginRepository, tokenManager: TokenManager): DataManager
            = DataManager(profileRepository, loginRepository, tokenManager)
}