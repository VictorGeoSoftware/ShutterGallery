package com.training.victor.development

import com.training.victor.development.di.TestNetworkModule
import com.training.victor.development.di.components.NetworkComponent
import com.training.victor.development.di.modules.DataManagerModule
import com.training.victor.development.di.modules.NetworkModule
import com.training.victor.development.di.modules.TokenManagerModule
import dagger.Component
import org.junit.Before
import javax.inject.Singleton

abstract class ParentUnitTest {
    open lateinit var testNetworkComponent: TestNetworkComponent

    @Singleton
    @Component(modules = [NetworkModule::class, DataManagerModule::class, TokenManagerModule::class])
    interface TestNetworkComponent : NetworkComponent {
        fun inject(target: ImagesPresenterTest)
        fun inject(target: LoginPresenterTest)
    }

    @Before
    open fun setUp() {
        testNetworkComponent = DaggerParentUnitTest_TestNetworkComponent.builder()
            .networkModule(TestNetworkModule())
            .build()
    }

    protected abstract fun <T> createMockedPresenter(): T
}