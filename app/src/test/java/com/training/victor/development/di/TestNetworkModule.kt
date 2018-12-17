package com.training.victor.development.di

import com.training.victor.development.di.modules.NetworkModule
import com.training.victor.development.network.ImagesRepository
import org.mockito.Mockito
import retrofit2.Retrofit

class TestNetworkModule: NetworkModule() {
    override fun provideImagesRetrofit(retrofit: Retrofit): ImagesRepository {
//        return super.provideImagesRetrofit(retrofit)
        return Mockito.mock(ImagesRepository::class.java)
    }
}