package com.training.victor.development

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.training.victor.development.data.DataManager
import com.training.victor.development.network.LoginRepository
import com.training.victor.development.presenter.LoginPresenter
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest: ParentUnitTest() {

    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var loginRepository: LoginRepository
    @Mock lateinit var loginView: LoginPresenter.LoginView

    private lateinit var testScheduler: TestScheduler
    private lateinit var loginPresenter: LoginPresenter

    @Before
    override fun setUp() {
        super.setUp()

        testNetworkComponent.inject(this)
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        loginPresenter = createMockedPresenter()
    }

    override fun <T> createMockedPresenter(): T {
        val loginPresenter = LoginPresenter(testScheduler, testScheduler, dataManager)
        loginPresenter.view = loginView
        return loginPresenter as T
    }

    // todo :: wrong test case
    @Test
    fun `should call to authorization API and retrieve the URL for grant access to my ShutterBox account`() {
        loginPresenter.showUpGrantAccessUrl()
        testScheduler.triggerActions()

        verify(loginView, times(1)).onAccessGrantingUrlReceived(any())
    }

    // todo :: wrong test case
    @Test
    fun `should call to access token API and retrieve a session token value`() {
        val code = "fsiZbl0Rx-8IYXtV4_9zK4"
        loginPresenter.getAccessToken(code)
        testScheduler.triggerActions()

        verify(loginView, times(1)).onAccessTokenReady()
    }
}