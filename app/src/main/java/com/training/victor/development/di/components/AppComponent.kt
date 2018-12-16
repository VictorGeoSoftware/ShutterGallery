package com.training.victor.development.di.components

import android.app.Application
import com.training.victor.development.di.modules.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DataManagerModule::class, TokenManagerModule::class])
interface AppComponent {
    fun inject(application: Application)
    fun plus(presenterModule: PresenterModule): PresenterComponent
}