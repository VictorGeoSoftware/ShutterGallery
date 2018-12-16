package com.training.victor.development.di.components

import com.training.victor.development.di.modules.DataManagerModule
import com.training.victor.development.di.modules.TokenManagerModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataManagerModule::class, TokenManagerModule::class])
interface DataManagerComponent