package com.training.victor.development

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.training.victor.development.data.DataManager
import com.training.victor.development.network.ImagesRepository
import com.training.victor.development.presenter.ImagesPresenter
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class ImagesPresenterTest: ParentUnitTest() {

    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var imagesRepository: ImagesRepository
    @Mock lateinit var imagesView: ImagesPresenter.ImagesView

    private lateinit var testScheduler: TestScheduler
    private lateinit var imagesPresenter: ImagesPresenter

    @Before
    override fun setUp() {
        super.setUp()

        testNetworkComponent.inject(this)
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        imagesPresenter = createMockedPresenter()
    }

    override fun <T> createMockedPresenter(): T {
        val profilesPresenter = ImagesPresenter(testScheduler, testScheduler, dataManager)
        profilesPresenter.view = imagesView
        return profilesPresenter as T
    }


    // --------------------------------------------- TESTING CASES ---------------------------------------------
    @Test
    fun `should call to image list API and retrieve a image list`() {
        val keyWord = "cars"
        imagesPresenter.getImageList(keyWord)
        testScheduler.triggerActions()

        verify(imagesView, times(1)).onImageListReceived(any())
    }

    @Test
    fun `should call to profiles list and retrieve an error`() {
        val keyWord = "cars"
        whenever(imagesRepository.getImagesList(keyWord)).thenReturn(Observable.error(Throwable()))
        imagesPresenter.getImageList(keyWord)
        testScheduler.triggerActions()

        verify(imagesView, times(1)).onImageListError()
    }
}
