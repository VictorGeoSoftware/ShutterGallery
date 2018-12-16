package com.training.victor.development.di.modules

import com.training.victor.development.data.TokenManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TokenManagerModule {
    @Provides
    @Singleton
    fun provideTokenManager() = TokenManager()
}